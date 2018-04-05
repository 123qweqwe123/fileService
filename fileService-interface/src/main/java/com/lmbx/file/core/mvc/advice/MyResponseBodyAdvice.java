package com.lmbx.file.core.mvc.advice;

import com.github.pagehelper.Page;
import com.google.common.collect.Maps;
import com.lmbx.file.core.mybatis.domain.TableResponse;

import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * 
 * <pre>
 *     根据返回的 ResponseBody 类型来判断是否拼装分页参数
 * </pre>
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /**
     * 方便前端调用， 分页： data.list,data.page， 普通json结果，如果返回的是数组，则 data.array(array在拼装...data参数时，会变为 index:data[index]的形式)，
     * 其它的直接返回， 其它结果直接返回
     * 
     * <pre class="code">
     * return {
     * success: true,
     * status,
     * ...data
     * }
     * </pre>
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 静态页面无需拦截
        if (body instanceof Resource) {
            return body;
        }
        if (body == null) {
            return null;
        }
        // 分页
        if (body instanceof Page) {
            Page page = (Page) body;
            TableResponse<Page> result = new TableResponse<>();
            result.setTotal(page.getTotal());
            result.setList(page);
            return result;
        }
        // 如果返回的是数组，前端通过 data.array 来获取
        if (body instanceof List || body.getClass().isArray()) {
            Map<String, Object> result = Maps.newHashMap();
            result.put("array", body);
            return result;
        }
        // 直接返回 string，页面可以通过 data.result 来获取数据
        if (body instanceof String) {
            body = "{\"result\":\"" + body + "\"}";
            return body;
        }
        // 返回的对象
        Map<String, Object> result = Maps.newHashMap();
        result.put("result", body);
        return result;
    }


}

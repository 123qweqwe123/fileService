import React from 'react'
import PropTypes from 'prop-types'
import { Table, Row, Col, Modal } from 'antd'
import classnames from 'classnames'
import { DropOption } from 'components'
import { Link } from 'react-router-dom'
import queryString from 'query-string'
import AnimTableBody from 'components/DataTable/AnimTableBody'
import styles from './List.less'

const { confirm } = Modal

const List = ({
 isMotion, location, ...tableProps
}) => {
  const { download,lookfile } = tableProps;
  location.query = queryString.parse(location.search)

  const handleMenuClick = (record, e) => {
    download(record.fileId)
  }

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
      className: styles.avatar,
      width: '5%',
      //render: text => <img alt="avatar" width={24} src={text} />,
    }, {
      title: '名称',
      dataIndex: 'name',
      key: 'name',
      width: '10%',
      //render: (text, record) => <Link to={`user/${record.id}`}>{text}</Link>,
    }, {
      title: '文件大小(M)',
      dataIndex: 'filesize',
      key: 'filesize',
      width: '5%',
    }, {
      title: '上传时间',
      dataIndex: 'uploadTime',
      key: 'uploadTime',
      width: '15%',
    }, 
    {
      title: '文件格式',
      dataIndex: 'type',
      key: 'type',
      width: '5%',
    }, 
    {
      title: '项目标识',
      dataIndex: 'project',
      key: 'project',
      width: '5%',
    }, 
    {
      title: '文件类别',
      dataIndex: 'filetype',
      key: 'filetype',
      width: '5%',
    }, 

    {
      title: '文件id',
      dataIndex: 'fileid',
      key: 'fileid',
      width: '20%',
    }, 
    {
      title: '文件类型',
      dataIndex: 'contenttype',
      key: 'contenttype',
      width: '15%',
    },
     {
      title: '操作',
      dataIndex: 'operator',
      width: '15%',
      render: (text, record) => (
        <span>
          <a href="javascript:void(0)" onClick={e => download(record.fileid)}>下载</a>
          {/* <span className="ant-divider" />
          <span className="ant-divider" />
          <span className="ant-divider" /> */}
           <span className="ant-divider" />
          <a href="javascript:void(0)" onClick={() => lookfile(record)}>查看</a>
        </span>),
    },
  ]

  const AnimateBody = (props) => {
    return <AnimTableBody {...props} />
  }

  const CommonBody = (props) => {
    return <tbody {...props} />
  }

  return (
    <Table
      {...tableProps}
      className={classnames(styles.table, { [styles.motion]: isMotion })}
      bordered
      scroll={{ y: 400 }}
      columns={columns}
      simple
      rowKey={record => record.id}
      components={{
        body: { wrapper: isMotion ? AnimateBody : CommonBody },
      }}
    />
  )
}

List.propTypes = {
  onDeleteItem: PropTypes.func,
  onEditItem: PropTypes.func,
  isMotion: PropTypes.bool,
  location: PropTypes.object,
}

export default List

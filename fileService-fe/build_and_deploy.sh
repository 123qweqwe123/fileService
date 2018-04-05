#!/usr/bin/env bash
cd ~/work/java/gitlab/scm/scm-fe/
# npm run build

cp -rf dist/* ~/work/java/gitlab/scm/scm-server/src/main/resources/static/

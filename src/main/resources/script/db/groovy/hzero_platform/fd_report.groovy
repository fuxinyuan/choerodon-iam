package script.db

databaseChangeLog(logicalFilePath: 'script/db/fd_report.groovy') {
    changeSet(author: 'xausky@163.com', id: '2019-09-10-fd-report') {
        createTable(tableName: "FD_REPORT") {
            column(name: 'ID', type: 'BIGINT UNSIGNED', autoIncrement: true, remarks: '主键ID') {
                constraints(primaryKey: true, primaryKeyName: 'PK_FD_REPORT')
            }
            column(name: 'REPORT_TYPE', type: 'VARCHAR(64)', remarks: '类型')
            column(name: 'ICON', type: 'MEDIUMTEXT', remarks: '图标')
            column(name: 'TITLE', type: 'VARCHAR(64)', remarks: '标题')
            column(name: 'DESCRIPTION', type: 'VARCHAR(128)', remarks: '描述')
            column(name: 'PATH', type: 'VARCHAR(128)', remarks: '路径')
            column(name: 'SORT', type: 'BIGINT UNSIGNED', remarks: '排序')


            column(name: "OBJECT_VERSION_NUMBER", type: "BIGINT UNSIGNED", defaultValue: "1")
            column(name: "CREATED_BY", type: "BIGINT UNSIGNED", defaultValue: "0")
            column(name: "CREATION_DATE", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
            column(name: "LAST_UPDATED_BY", type: "BIGINT UNSIGNED", defaultValue: "0")
            column(name: "LAST_UPDATE_DATE", type: "DATETIME", defaultValueComputed: "CURRENT_TIMESTAMP")
        }
    }
}
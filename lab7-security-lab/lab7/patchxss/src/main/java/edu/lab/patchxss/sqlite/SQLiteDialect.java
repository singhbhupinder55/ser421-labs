package edu.lab.patchxss.sqlite;
import java.sql.Types;

import org.hibernate.dialect.Dialect;

public class SQLiteDialect extends Dialect {
    // Ref: https://stackoverflow.com/questions/24232892/spring-boot-and-sqlite
    public SQLiteDialect() {
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGNVARCHAR, "longvarchar");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
    }

    public boolean supportsIdentityColumns() {
        return true;
    }
 
    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }
 
    public String getIdentityColumnString() {
        return "integer";
    }
 
    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }
 
    public boolean supportsLimit() {
        return true;
    }
 
    protected String getLimitString(String query, boolean hasOffset) {
        return new StringBuffer(query.length() + 20).append(query).append(hasOffset ? " limit ? offset ?" : " limit ?")
                .toString();
    }
 
    public boolean supportsTemporaryTables() {
        return true;
    }
 
    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }
 
    public boolean dropTemporaryTableAfterUse() {
        return false;
    }
 
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }
 
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }
 
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }
 
    public boolean supportsUnionAll() {
        return true;
    }
 
    public boolean hasAlterTable() {
        return false; // As specify in NHibernate dialect
    }
 
    public boolean dropConstraints() {
        return false;
    }
 
    public String getAddColumnString() {
        return "add column";
    }
 
    public String getForUpdateString() {
        return "";
    }
 
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }
 
    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
    }
 
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable,
            String[] primaryKey, boolean referencesPrimaryKey) {
        throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
    }
 
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
    }
 
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }
 
    public boolean supportsCascadeDelete() {
        return false;
    }
}

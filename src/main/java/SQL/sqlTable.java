package SQL;

import java.util.Vector;

public class sqlTable<T> {
    String tableName;
    Vector<T> columnNames;
    Vector<Vector> rows;
    Vector<T> rowEntry;


    public sqlTable(){
        this.tableName = null;
        this.columnNames = new Vector<>();
        this.rows = new Vector<>();
        this.rowEntry = new Vector<>();
    }

    public sqlTable(String tableName){
        this.tableName = tableName;
        this.columnNames = new Vector<>();
        this.rows = new Vector<>();
        this.rowEntry = new Vector<>();
    }

    public sqlTable(String tableName, Vector<T> columnNames){
        this.tableName = tableName;
        this.columnNames = columnNames;
        this.rows = new Vector<>();
        this.rowEntry = new Vector<>();
    }

    public sqlTable(String tableName, Vector<T> columnNames, Vector<Vector> rows, Vector<T> rowEntry){
        this.tableName = tableName;
        this.columnNames = columnNames;
        this.rows = rows;
        this.rowEntry = rowEntry;
    }

    public String getTableName() {
        return tableName;
    }

    public Vector<T> getColumnNames() {
        return columnNames;
    }

    public Vector<Vector> getRows() {
        return rows;
    }

    public Vector<T> getRowEntry() {
        return rowEntry;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setColumnNames(Vector<T> columnNames) {
        this.columnNames = columnNames;
    }

    public void setRows(Vector<Vector> rows) {
        this.rows = rows;
    }

    public void setRowEntry(Vector<T> rowEntry) {
        this.rowEntry = rowEntry;
    }

    @Override
    public String toString() {
        String newS = "sqlTable{" +
                "tableName='" + tableName + '\'' +
                ", columnNames=" + columnNames.toString() +
                ", rows=" + rows.elements().toString() +
                '}';


        return newS;
    }
}

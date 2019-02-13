package com.model.login.purchase.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class POItemsRoot {

@SerializedName("Table")
@Expose
private List<Table> table = null;
@SerializedName("Table1")
@Expose
private List<Table1> table1 = null;
@SerializedName("Table2")
@Expose
private List<Table2> table2 = null;

public List<Table> getTable() {
return table;
}

public void setTable(List<Table> table) {
this.table = table;
}

public List<Table1> getTable1() {
return table1;
}

public void setTable1(List<Table1> table1) {
this.table1 = table1;
}

public List<Table2> getTable2() {
return table2;
}

public void setTable2(List<Table2> table2) {
this.table2 = table2;
}

}
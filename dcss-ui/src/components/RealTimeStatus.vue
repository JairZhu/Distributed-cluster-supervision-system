<template>
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      prop="iP"
      label="IP"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="客户端名称"
      width="180">
    </el-table-column>
    <el-table-column
      prop="cpu"
      label="CPU核数"
      width="180">
    </el-table-column>
    <el-table-column
      prop="os"
      label="操作系统"
      width="180">
    </el-table-column>
    <el-table-column
      prop="online"
      label="状态"
      width="180"
      filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag
          :type="scope.row.online? 'success' : 'danger'"
          disable-transitions>{{scope.row.online? '在线':'离线'}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column align="right">
      <template slot="header" >
        <el-button @click="handlePrint()" icon="el-icon-printer" type="info" circle></el-button>
      </template>
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" icon="el-icon-zoom-in" type="warning" circle></el-button>
        <el-button @click="handleEdit(scope.row)" icon="el-icon-edit" type="primary" circle></el-button>
        <el-button @click="handleDelete(scope.row)" icon="el-icon-delete" type="danger" circle></el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "RealTimeStatus",
  methods: {
    handleClick(row) {
      if (row.online) {
        this.$router.push("/realTimeGraph");
      }
      else {
        this.$router.push("/historyGraph");
      }
    },
    handleEdit(row) {
      this.$http.post("http://localhost:8011/editClient", row);
      location.reload();
    },
    handlePrint() {
      window.print();
    },
    handleDelete(row) {
      this.$http.post("http://localhost:8011/deleteClient", row);
      location.reload();
    },
  },
  data() {
    return {
      tableData: []
    }
  },
  created() {
    this.$http.get("http://localhost:8011/clients").then(res=>{
      console.log(res.data);
      this.tableData = res.data;
    })
  }
}
</script>

<style>

</style>

<template>
  <div id="main" style="width: 600px;height:400px;"></div>
</template>

<script>

import echarts from "echarts";

export default {
  name: "HistoryGraph",
  mounted() {
    this.$http.get("http://localhost:8081/historyClientsReport").then(res=>{
      console.log(res);
    });

    this.drawLine();
  },
  methods: {
    drawLine() {
      var myChart = echarts.init(document.getElementById('main'));
      var option = {
        title: {
          text: "标题"
        },
        tooltip: {},
        legend: {
          data:['销量']
        },
        xAxis: {
          data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},
        series: [{
          name: '销量',
          type: 'line',
          data: [5, 20, 36, 10, 10, 20]
        }]
      };
      myChart.setOption(option);
    }
  }
}
</script>

<style scoped>

</style>

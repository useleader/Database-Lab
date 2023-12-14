<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 组合查询
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-select v-model="query.type" placeholder="类型" class="handle-select mr10">
          <el-option key="%" label="不限" value="%"></el-option>
          <el-option key="Action"      label="Action"      value="Action"></el-option>
          <el-option key="Adventure"   label="Adventure"   value="Adventure"></el-option>
          <el-option key="Animation"   label="Animation"   value="Animation"></el-option>
          <el-option key="Biography"   label="Biography"   value="Biography"></el-option>
          <el-option key="Comedy"      label="Comedy"      value="Comedy"></el-option>
          <el-option key="Crime"       label="Crime"       value="Crime"></el-option>
          <el-option key="Documentary" label="Documentary" value="Documentary"></el-option>
          <el-option key="Drama"       label="Drama"       value="Drama"></el-option>
          <el-option key="Family"      label="Family"      value="Family"></el-option>
          <el-option key="Fantasy"     label="Fantasy"     value="Fantasy"></el-option>
          <el-option key="Film-Noir"   label="Film-Noir"   value="Film-Noir"></el-option>
          <el-option key="Game-Show"   label="Game-Show"   value="Game-Show"></el-option>
          <el-option key="History"     label="History"     value="History"></el-option>
          <el-option key="Horror"      label="Horror"      value="Horror"></el-option>
          <el-option key="Music"       label="Music"       value="Music"></el-option>
          <el-option key="Musical"     label="Musical"     value="Musical"></el-option>
          <el-option key="Mystery"     label="Mystery"     value="Mystery"></el-option>
          <el-option key="News"        label="News"        value="News"></el-option>
          <el-option key="Reality-TV"  label="Reality-TV"  value="Reality-TV"></el-option>
          <el-option key="Romance"     label="Romance"     value="Romance"></el-option>
          <el-option key="Sci-Fi"      label="Sci-Fi"      value="Sci-Fi"></el-option>
          <el-option key="Short"       label="Short"       value="Short"></el-option>
          <el-option key="Sport"       label="Sport"       value="Sport"></el-option>
          <el-option key="Thriller"    label="Thriller"    value="Thriller"></el-option>
          <el-option key="War"         label="War"         value="War"></el-option>
          <el-option key="Western"     label="Western"     value="Western"></el-option>
        </el-select>

        <el-select v-model="query.country" placeholder="国家" class="handle-select mr10">
          <el-option key="不限" label="不限" value="不限"></el-option>
          <el-option key="美国" label="美国" value="美国"></el-option>
          <el-option key="英国" label="英国" value="英国"></el-option>
          <el-option key="法国" label="法国" value="法国"></el-option>
          <el-option key="意大利" label="意大利" value="意大利"></el-option>
          <el-option key="德国" label="德国" value="德国"></el-option>
          <el-option key="中国大陆" label="中国大陆" value="中国大陆"></el-option>
          <el-option key="中国台湾" label="中国台湾" value="中国台湾"></el-option>
          <el-option key="中国香港" label="中国香港" value="中国香港"></el-option>
          <el-option key="日本" label="日本" value="日本"></el-option>
          <el-option key="韩国" label="韩国" value="韩国"></el-option>
          <el-option key="巴西" label="巴西" value="巴西"></el-option>
          <el-option key="新西兰" label="新西兰" value="新西兰"></el-option>
          <el-option key="加拿大" label="加拿大" value="加拿大"></el-option>
          <el-option key="澳大利亚" label="澳大利亚" value="澳大利亚"></el-option>
          <el-option key="墨西哥" label="墨西哥" value="墨西哥"></el-option>
        </el-select>
        <div class="handle-box">
          <el-input v-model="query.min_score" placeholder="最小评分(0~10)" class="handle-input mr10"></el-input>
          <el-input v-model="query.max_score" placeholder="最大评分(0~10)" class="handle-input mr10"></el-input>
        </div>
        <div class="handle-box">
          <el-input v-model="query.min_duration" placeholder="最短时间(格式为hh:mm:ss)" class="handle-input mr10"></el-input>
          <el-input v-model="query.max_duration" placeholder="最长时间(格式为hh:mm:ss)" class="handle-input mr10"></el-input>
        </div>
        <el-button type="success" @click="handleSearch">搜索</el-button>
      </div>
      <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
        <el-table-column prop="alias" width="100" label="电影ID"></el-table-column>
        <el-table-column prop="name" label="电影名" href="MoviePage"  align="center"></el-table-column>
        <el-table-column prop="grade" width="100" label="评分"></el-table-column>
        <el-table-column prop="type" width="100" label="类型"></el-table-column>
        <el-table-column prop="country" width="100" label="国家"></el-table-column>
        <el-table-column prop="date" width="100" label="上映时间"></el-table-column>
        <el-table-column prop="duration" width="100" label="影片时长"></el-table-column>
        <el-table-column label="打分" width="90" align="center">
          <template #default="scope">
            <el-button type="text" icon="el-icon-edit"
                       @click="handleEdit(scope.$index, scope.row)">打分</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
                       :page-size="query.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
      </div>
    </div>
    <!-- 这里弹窗增加电影 -->
    <el-dialog title="评分" v-model="editVisible" width="30%">
      <div class="demo-rate-block">
        <span class="demonstration">给这部电影打个分吧</span>
        <el-rate size="large" v-model="my_grade" :colors="colors" allow-half /> {{(my_grade * 2)}}
      </div>
      <template #footer>
                <span class="dialog-footer">
                    <el-button @click="saveEdit" type="primary" plain size="small">确定</el-button>
                    <el-button @click="onReset" type="primary" plain size="small">重置</el-button>
                </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive, onMounted} from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import axios from "axios";
import qs from "qs";

export default {
  name: "basetable",
  setup() {
    const query = reactive({
      type: "",
      country: "",
      min_score: "",
      max_score: "",
      min_duration: "",
      max_duration: "",
      pageIndex: 1, // 当前页
      pageSize: 10, // 每页有多少项
    });
    const TableData = ref([]); // 总表
    const tableData = ref([]); // 当前呈现出的一页，从总表中获得
    const pageTotal = ref(0);

    const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']);

    const my_grade = ref(null);
    const send_grade = reactive({
      name: "",
      grade: my_grade,
    })


    const getData = () => {
      tableData.value = TableData.value.slice(
          query.pageSize*(query.pageIndex-1),
          query.pageSize*(query.pageIndex)
      );
    }
    // onMounted(() => {
    //   // 这里都不需要传参
    //     axios.get(localStorage.getItem("ip") + "select_all").then(
    //         function (response) {
    //           let list = response.data.result;
    //           TableData.value = list;
    //           tableData.value = list.slice(
    //               query.pageSize*(query.pageIndex-1),
    //               query.pageSize*(query.pageIndex));
    //           pageTotal.value = list.length;
    //         }
    //     );
    // });
    // 查询操作
    const handleSearch = () => {
      query.pageIndex = 1;
      let sendpara = qs.stringify(query);
      axios.post(localStorage.getItem("ip") + "/movielist/combining", sendpara).then(
          function (response){
            let list = response.data.result;
            TableData.value = list;
            tableData.value = list.slice(
                query.pageSize*(query.pageIndex-1),
                query.pageSize*(query.pageIndex));
            pageTotal.value = list.length;
          }
      );
      getData();
    };
    // 分页导航
    const handlePageChange = (val) => {
      query.pageIndex = val;
      getData();
    };

    const handleEdit = (index, row) => {
      send_grade.name = row.name;
      editVisible.value = true;
    };

    // 打分
    const editVisible = ref(false);
    const saveEdit = () => {
      editVisible.value = false;
      send_grade.grade = my_grade.value * 2;
      let sendpara = qs.stringify(send_grade);
      axios.post(localStorage.getItem("ip") + "update_grade", sendpara).then(
          function (response){
            let dt = response.data.result;
            if (dt === true){
              ElMessage.success("评分成功");
            } else {
              ElMessage.error("评分失败");
              return false;
            }
          },
          function (err){
            ElMessage.error("评分失败");
            console.log(err);
          });
    };
    const onReset = () => {
      my_grade.value = 0;
    };

    return {
      query,
      tableData,
      pageTotal,
      editVisible,
      my_grade,
      colors,
      handleSearch,
      handlePageChange,
      handleEdit,
      saveEdit,
      onReset,

    };
  },
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}


.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}
.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}

.demo-rate-block {
  padding: 0px;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 90%;
  box-sizing: border-box;
}
.demo-rate-block:last-child {
  border-right: none;
}
.demo-rate-block .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}

</style>


<style>
.el-pagination.is-background.el-pager li:not(.disabled){
  background-color:#fff;/*进行修改未选中背景和字体*/
  color:#fff;
}

.el-pagination.is-background.el-pager li:not(.disabled).active{
  background-color:#0da5aa;/*进行修改选中项背景和字体*/
  color:#fff;
}

.el-pagination.is-background .el-pager li:hover{
  color: #8bc34a !important;/*hover时候的文字颜色*/
}
.el-pager li.active{
  color:#fff !important;/*仅设置active时的文字颜色*/
}
</style>

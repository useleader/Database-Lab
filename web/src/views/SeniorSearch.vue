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

        <el-select v-model="query.country" placeholder="地区" class="handle-select mr10">
          <el-option key="%" label="不限" value="%"></el-option>
          <el-option key="USA" label="USA" value="USA"></el-option>
          <el-option key="UK" label="UK" value="UK"></el-option>
          <el-option key="New Zealand" label="New Zealand" value="New Zealand"></el-option>
          <el-option key="Canada" label="Canada" value="Canada"></el-option>
          <el-option key="Australia" label="Australia" value="Australia"></el-option>
          <el-option key="Belgium" label="Belgium" value="Belgium"></el-option>
          <el-option key="Japan" label="Japan" value="Japan"></el-option>
          <el-option key="Germany" label="Germany" value="Germany"></el-option>
          <el-option key="China" label="China" value="China"></el-option>
          <el-option key="France" label="France" value="France"></el-option>
          <el-option key="" label="" value=""></el-option>
          <el-option key="New Line" label="New Line" value="New Line"></el-option>
          <el-option key="Mexico" label="Mexico" value="Mexico"></el-option>
          <el-option key="Spain" label="Spain" value="Spain"></el-option>
          <el-option key="Hong Kong" label="Hong Kong" value="Hong Kong"></el-option>
          <el-option key="Czech Republic" label="Czech Republic" value="Czech Republic"></el-option>
          <el-option key="India" label="India" value="India"></el-option>
          <el-option key="Soviet Union" label="Soviet Union" value="Soviet Union"></el-option>
          <el-option key="South Korea" label="South Korea" value="South Korea"></el-option>
          <el-option key="Peru" label="Peru" value="Peru"></el-option>
          <el-option key="Italy" label="Italy" value="Italy"></el-option>
          <el-option key="Russia" label="Russia" value="Russia"></el-option>
          <el-option key="Aruba" label="Aruba" value="Aruba"></el-option>
          <el-option key="Denmark" label="Denmark" value="Denmark"></el-option>
          <el-option key="Libya" label="Libya" value="Libya"></el-option>
          <el-option key="Ireland" label="Ireland" value="Ireland"></el-option>
          <el-option key="South Africa" label="South Africa" value="South Africa"></el-option>
          <el-option key="Iceland" label="Iceland" value="Iceland"></el-option>
          <el-option key="Switzerland" label="Switzerland" value="Switzerland"></el-option>
          <el-option key="Romania" label="Romania" value="Romania"></el-option>
          <el-option key="West Germany" label="West Germany" value="West Germany"></el-option>
          <el-option key="Chile" label="Chile" value="Chile"></el-option>
          <el-option key="Netherlands" label="Netherlands" value="Netherlands"></el-option>
          <el-option key="Hungary" label="Hungary" value="Hungary"></el-option>
          <el-option key="Panama" label="Panama" value="Panama"></el-option>
          <el-option key="Greece" label="Greece" value="Greece"></el-option>
          <el-option key="Sweden" label="Sweden" value="Sweden"></el-option>
          <el-option key="Norway" label="Norway" value="Norway"></el-option>
          <el-option key="Taiwan" label="Taiwan" value="Taiwan"></el-option>
          <el-option key="Official site" label="Official site" value="Official site"></el-option>
          <el-option key="Cambodia" label="Cambodia" value="Cambodia"></el-option>
          <el-option key="Thailand" label="Thailand" value="Thailand"></el-option>
          <el-option key="Slovakia" label="Slovakia" value="Slovakia"></el-option>
          <el-option key="Bulgaria" label="Bulgaria" value="Bulgaria"></el-option>
          <el-option key="Iran" label="Iran" value="Iran"></el-option>
          <el-option key="Poland" label="Poland" value="Poland"></el-option>
          <el-option key="Georgia" label="Georgia" value="Georgia"></el-option>
          <el-option key="Turkey" label="Turkey" value="Turkey"></el-option>
          <el-option key="Nigeria" label="Nigeria" value="Nigeria"></el-option>
          <el-option key="Brazil" label="Brazil" value="Brazil"></el-option>
          <el-option key="Finland" label="Finland" value="Finland"></el-option>
          <el-option key="Bahamas" label="Bahamas" value="Bahamas"></el-option>
          <el-option key="Argentina" label="Argentina" value="Argentina"></el-option>
          <el-option key="Colombia" label="Colombia" value="Colombia"></el-option>
          <el-option key="Israel" label="Israel" value="Israel"></el-option>
          <el-option key="Egypt" label="Egypt" value="Egypt"></el-option>
          <el-option key="Kyrgyzstan" label="Kyrgyzstan" value="Kyrgyzstan"></el-option>
          <el-option key="Indonesia" label="Indonesia" value="Indonesia"></el-option>
          <el-option key="Pakistan" label="Pakistan" value="Pakistan"></el-option>
          <el-option key="Slovenia" label="Slovenia" value="Slovenia"></el-option>
          <el-option key="Afghanistan" label="Afghanistan" value="Afghanistan"></el-option>
          <el-option key="Dominican Republic" label="Dominican Republic" value="Dominican Republic"></el-option>
          <el-option key="Cameroon" label="Cameroon" value="Cameroon"></el-option>
          <el-option key="United Arab Emirates" label="United Arab Emirates" value="United Arab Emirates"></el-option>
          <el-option key="Kenya" label="Kenya" value="Kenya"></el-option>
          <el-option key="Philippines" label="Philippines" value="Philippines"></el-option>
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
        <el-table-column prop="id" width="100" label="电影ID"></el-table-column>
        <el-table-column prop="name" label="电影名" align="center">
          <template v-slot="scope">
            <el-button type="text"
                       @click="goToMoviePage(scope.row)">{{ scope.row.name }}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="tags" width="100" label="类型"></el-table-column>
        <el-table-column prop="region" width="100" label="国家"></el-table-column>
        <el-table-column prop="director" width="100" label="导演"></el-table-column>
        <el-table-column prop="str_duration" width="100" label="影片时长"></el-table-column>
        <el-table-column prop="avg_score" width="100" label="电影评分"></el-table-column>
        <el-table-column label="打分" width="90" align="center">
          <template v-slot="scope">
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
import router from "../router";

export default {

  name: "basetable",
  setup() {
    const query = reactive({
      type: "",
      country: "",
      min_score: 0,
      max_score: 10,
      min_duration: "00:00:00",
      max_duration: "10:10:10",
      pageIndex: 1, // 当前页
      pageSize: 10, // 每页有多少项
    });
    const TableData = ref([]); // 总表
    const tableData = ref([]); // 当前呈现出的一页，从总表中获得
    const pageTotal = ref(0);

    const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']);

    const my_grade = ref(null);
    const send_grade = reactive({
      film_id: 0,
      user_id: 0,
      grade: 0,
    })


    const getData = () => {
      tableData.value = TableData.value.slice(
          query.pageSize*(query.pageIndex-1),
          query.pageSize*(query.pageIndex)
      );
    }
    // 查询操作
    const handleSearch = () => {
      query.pageIndex = 1;
      let sendpara = qs.stringify(query);
      axios.post(localStorage.getItem("ip") + "/movielist/combining", sendpara).then(
          function (response){
            let list = response.data;
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
      send_grade.film_id = row.id;
      editVisible.value = true;
    };

    // 打分
    const editVisible = ref(false);
    const saveEdit = () => {
      editVisible.value = false;
      send_grade.grade = my_grade.value * 2;
      send_grade.user_id = localStorage.getItem("user_id");
      let sendpara = qs.stringify(send_grade);
      axios.post(localStorage.getItem("ip") + "/score/insert", sendpara).then(
          function (response){
            let dt = response.data;
            if (dt === 1){
              ElMessage.success("评分成功");
              location.reload();
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
  methods: {
    //跳转到详情页
    goToMoviePage(row) {
      router.push({ name: 'moviepage', params: { id: row.id } });
    },

  }
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

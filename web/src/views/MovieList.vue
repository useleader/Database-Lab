<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 电影列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.name" placeholder="电影名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="primary" icon="el-icon-plus" plain @click="(editVisible = true)">添加电影</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="name" label="电影名" align="center"></el-table-column>
                <el-table-column prop="poster" label="电影截图" align="center"></el-table-column>
                <el-table-column prop="grade" width="100" label="评分"></el-table-column>
                <el-table-column prop="type" width="100" label="类型"></el-table-column>
                <el-table-column prop="country" width="100" label="地区"></el-table-column>
                <el-table-column prop="language" width="100" label="语言"></el-table-column>
                <el-table-column prop="date" width="100" label="上映日期"></el-table-column>
                <el-table-column prop="duration" width="100" label="影片时长"></el-table-column>
                <el-table-column prop="alias" width="100" label="ID"></el-table-column>
                <el-table-column prop="director" label="导演"></el-table-column>
                <el-table-column prop="performer" label="演员"></el-table-column>
                <el-table-column prop="scriptwriter" label="编剧"></el-table-column>
                <el-table-column label="操作" width="90" align="center">
                    <template #default="scope">
                        <el-button type="text" icon="el-icon-delete" class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
                    :page-size="query.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>
        <!-- 这里弹窗增加电影 -->
        <el-dialog title="添加电影" v-model="editVisible" width="50%">
            <el-form ref="formRef" :rules="rules" :model="form" label-width="80px">
                <el-row :gutter="20">
                    <el-col :span="11">
                        <el-form-item label="电影名">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="导演">
                            <el-input v-model="form.director"></el-input>
                        </el-form-item>
                        <el-form-item label="编剧">
                            <el-input v-model="form.scriptwriter"></el-input>
                        </el-form-item>
                        <el-form-item label="演员">
                            <el-input v-model="form.performer"></el-input>
                        </el-form-item>
                        <el-form-item label="类型">
                            <el-input v-model="form.type"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="11">
                        <el-form-item label="地区">
                            <el-input v-model="form.country"></el-input>
                        </el-form-item>
                        <el-form-item label="语言">
                            <el-input v-model="form.language"></el-input>
                        </el-form-item>
                        <el-form-item label="上映日期">
                            <el-input v-model="form.date"></el-input>
                        </el-form-item>
                        <el-form-item label="影片时长">
                            <el-input v-model="form.duration"></el-input>
                        </el-form-item>
                        <el-form-item label="ID">
                            <el-input v-model="form.alias"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="saveEdit" type="primary" plain>确定</el-button>
                    <el-button @click="onReset" type="primary" plain>重置</el-button>
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
            name: "",
            pageIndex: 1, // 当前页
            pageSize: 10, // 每页有多少项
        });
        const TableData = ref([]); // 总表
        const tableData = ref([]); // 当前呈现出的一页，从总表中获得
        const pageTotal = ref(0);
        
        const getData = () => {
            tableData.value = TableData.value.slice(
                query.pageSize*(query.pageIndex-1),
                query.pageSize*(query.pageIndex)
            );
        }
        onMounted(() => {
          // 这里都不需要传参
            axios.get(localStorage.getItem("ip") + "select_all").then(
                function (response) {
                  let list = response.data.result;
                  TableData.value = list;
                  tableData.value = list.slice(
                      query.pageSize*(query.pageIndex-1),
                      query.pageSize*(query.pageIndex));
                  pageTotal.value = list.length;
                }
            )
        })
        getData();
        // 查询操作
        const handleSearch = () => {
            query.pageIndex = 1;
            getData();
        };
        // 分页导航
        const handlePageChange = (val) => {
            query.pageIndex = val;
            getData();
        };

        // 删除操作
        const handleDelete = (index, row) => {
            // 二次确认删除
            ElMessageBox.confirm("确定要删除吗？", "提示", {
                type: "warning",
            })
                .then(() => {
                    let sendpara = qs.stringify(row);
                    axios.post(localStorage.getItem("ip") + "delete_movie", sendpara).then(
                        function (response) {
                            console.log(row);
                            let dt = response.data.result;
                            if (dt == true) {
                                ElMessage.success("删除成功");
                            } else {
                                ElMessage.error("删除失败");
                                return false;
                            }
                        }
                    )
                })
                .catch(() => {});
            getData();
        };
        // 添加操作，这里可能需要注意属性数据类型的转换
        const editVisible = ref(false);
        const formRef = ref(null);
        const form = reactive({
            name:"",
            director:"",
            scriptwriter:"",
            performer:"",
            type:"",
            country:"",
            language:"",
            date:"",
            duration:"",
            alias:"",
        });
        const rules = {
            name: [
                { required: true, message: "请输入电影名", trigger: "blur" },
            ],
            director: [
                { required: true, message: "请输入导演", trigger: "blur" },
            ],
            scriptwriter: [
                { required: true, message: "请输入编剧", trigger: "blur" },
            ],
            performer: [
                { required: true, message: "请输入演员", trigger: "blur" },
            ],
            type: [
                { required: true, message: "请输入类型", trigger: "blur" },
            ],
            country: [
                { required: true, message: "请输入国家", trigger: "blur" },
            ],
            language: [
                { required: true, message: "请输入语言", trigger: "blur" },
            ],
            date: [
                { required: true, message: "请输入上映时间，格式如20220101", trigger: "blur" },
            ],
            duration: [
                { required: true, message: "请输入影片时长，单位为分钟", trigger: "blur" },
            ],
            alias: [
                { required: false, message: "", trigger: "blur" },
            ],
        };
        const saveEdit = () => {
            editVisible.value = false;
            formRef.value.validate((valid) =>{
              if (valid){
                  let sendpara = qs.stringify(form);
                  axios.post(localStorage.getItem("ip") + "insert_movie", sendpara).then(
                      function (response){
                          console.log(form);
                          let dt = response.data.result;
                          if (dt === true){
                              ElMessage.success("添加成功");
                          } else {
                              ElMessage.error("添加失败");
                              return false;
                          }
                      },
                      function (err){
                          ElMessage.error("添加失败");
                          console.log(err);
                      });
              } else {
                console.log(valid);
                ElMessage.error("请填写完整信息");
                return false;
              }
          });
        };
        const onReset = () => {
            formRef.value.resetFields();
        };
        
        return {
            query,
            tableData,
            pageTotal,
            editVisible,
            form,
            formRef,
            rules,
            handleSearch,
            handlePageChange,
            handleDelete,
            saveEdit,
            onReset,
        };
    },
};
</script>

<style scoped>
.handle-box {
    display: flex;
    justify-content: center;
    align-items: center;
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
</style>
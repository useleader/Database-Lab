<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>
            <div class="clearfix">
              <span>个人主页</span>
            </div>
          </template>
          <div class="info">
            <div class="info-image" @click="showDialog">
              <img :src="movieData.photo" />
              <span class="info-edit">
                                <i class="el-icon-lx-camerafill"></i>
                            </span>
            </div>
            <div class="info-name">{{ movieData.name }}</div>
            <div class="info-desc">电影简介: {{ movieData.description }}</div>
            <div class="info-director">导演: {{ movieData.director }}</div>
            <div class="info-duration">电影时长: {{ movieData.duration }}</div>
            <div class="info-tag">电影标签{{ movieData.tag }}</div>
            <div class="info-region">电影产地：{{ movieData.region }}</div>
            <div class="info-name">参演演员：{{ movieData.actor }}</div>
            <div class="info-score">电影评分：{{ movieData.avg_score }}</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>
            <div class="clearfix">
              <span>编辑信息</span>
            </div>
          </template>
          <el-form label-width="90px">
            <el-form-item label="名称：">
              <el-input type="gender" v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="简介：">
              <el-input type="desc" v-model="form.description"></el-input>
            </el-form-item>
            <el-form-item label="导演：">
              <el-input type="director" v-model="form.director"></el-input>
            </el-form-item>
            <el-form-item label="电影时长：">
              <el-input type="duration" v-model="form.duration"></el-input>
            </el-form-item>
            <el-form-item label="标签：">
              <el-input type="tag" v-model="form.tag"></el-input>
            </el-form-item>
            <el-form-item label="地区：">
              <el-input type="region" v-model="form.region"></el-input>
            </el-form-item>
            <el-form-item label="演员：">
              <el-input type="gender" v-model="form.actor">用,间隔</el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">保存</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="裁剪图片" v-model="dialogVisible" width="600px">
      <vue-cropper ref="cropper" :src="imgSrc" :ready="cropImage" :zoom="cropImage" :cropmove="cropImage"
                   style="width: 100%; height: 400px"></vue-cropper>

      <template #footer>
                <span class="dialog-footer">
                    <el-button class="crop-demo-btn" type="primary">选择图片
                        <input class="crop-input" type="file" name="image" accept="image/*" @change="setImage" />
                    </el-button>
                    <el-button type="primary" @click="saveAvatar">上传并保存</el-button>
                </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import VueCropper from "vue-cropperjs";
import { useRoute } from 'vue-router';
import "cropperjs/dist/cropper.css";
import avatar from "../assets/img/user.jpg";
import { ref, reactive, onMounted} from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
import axios from 'axios';
import qs from "qs";
import router from "../router";

export default {
  name: "user",
  components: {
    VueCropper,
  },
  setup() {

    const route = useRoute();
    const movieID = route.params.id;
    onMounted(() => {

          fetchMoviedata(movieID);
        }
    );

    const form = reactive({
      id: movieID,
      name: '',
      description: '',
      director: '',
      photo: '',
      duration: '',
      tag: '',
      region: '',
      actor: '',
      avg_score: ''
    })
    const movieData = ref({
          name: '',
          description: '',
          director: '',
          photo: '',
          duration: '',
          tag: '',
          region: '',
          actor: '',
          avg_score: ''
        }
    );

    const fetchMoviedata = (movieID) => {
      const sendpara = qs.stringify({ id: movieID });
      axios.post(localStorage.getItem("ip") + '/movielist/selectById', sendpara).then(
          function (response) {
            const movieInfo = response.data;
            movieData.value = {
              id: movieInfo.id,
              name: movieInfo.name,
              description: movieInfo.description,
              director: movieInfo.director,
              photo: movieInfo.photo,
              duration: movieInfo.str_duration,
              tag: movieInfo.tags,
              region: movieInfo.region,
              actor: movieInfo.actors
            };
          }
      );
    };


    const onSubmit = () => {
      let sendpara = qs.stringify(form);
      axios.post(localStorage.getItem("ip")+"/movielist/update", sendpara).then(
          function (response){
            let ans = response.data;
            if (ans === 1) {
              ElMessage.success("更新成功");
              location.reload();
            } else {
              ElMessage.error("更新失败");
              return false;
            }
          }
      )
    };

    const avatarImg = ref(avatar);
    const imgSrc = ref("");
    const cropImg = ref("");
    const dialogVisible = ref(false);
    const cropper = ref(null);

    const showDialog = () => {
      dialogVisible.value = true;
      imgSrc.value = avatarImg.value;
    };

    const setImage = (e) => {
      const file = e.target.files[0];
      if (!file.type.includes("image/")) {
        return;
      }
      const reader = new FileReader();
      reader.onload = (event) => {
        dialogVisible.value = true;
        imgSrc.value = event.target.result;
        cropper.value && cropper.value.replace(event.target.result);
      };
      reader.readAsDataURL(file);
    };

    const cropImage = () => {
      cropImg.value = cropper.value.getCroppedCanvas().toDataURL();
    };

    const saveAvatar = () => {
      avatarImg.value = cropImg.value;
      dialogVisible.value = false;
    };

    return {
      movieData,
      name,
      form,
      onSubmit,
      cropper,
      avatarImg,
      imgSrc,
      cropImg,
      showDialog,
      dialogVisible,
      setImage,
      cropImage,
      saveAvatar,
    };
  },

};
</script>

<style scoped>
.info {
  text-align: center;
  padding: 35px 0;
}
.info-image {
  position: relative;
  margin: auto;
  width: 100px;
  height: 100px;
  background: #f8f8f8;
  border: 1px solid #eee;
  border-radius: 50px;
  overflow: hidden;
}
.info-image img {
  width: 100%;
  height: 100%;
}
.info-edit {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  opacity: 0;
  transition: opacity 0.3s ease;
}
.info-edit i {
  color: #eee;
  font-size: 25px;
}
.info-image:hover .info-edit {
  opacity: 1;
}
.info-name {
  margin: 15px 0 10px;
  font-size: 24px;
  font-weight: 500;
  color: #262626;
}

.info-gender,
.info-email,
.info-desc {
  margin-bottom: 8px;
}
.crop-demo-btn {
  position: relative;
}
.crop-input {
  position: absolute;
  width: 100px;
  height: 40px;
  left: 0;
  top: 0;
  opacity: 0;
  cursor: pointer;
}
</style>
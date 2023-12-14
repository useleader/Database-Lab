<template>
  <div>
    <h2>{{ movieData.name }}</h2>
    <p>{{ movieData.description }}</p>
    <p>导演: {{ movieData.director }}</p>

    <p>时长: {{ movieData.duration }} 分钟</p>
    <p>标签: {{ movieData.tag }}</p>
    <p>地区: {{ movieData.region }}</p>
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

    onMounted(() => {
          const movieID = route.params.id;
          fetchMoviedata(movieID);
        }
    );

    const movieData = ref({
          name: '',
          description: '',
          director: '',
          photo: '',
          duration: '',
          tag: '',
          region: ''
        }
    );

    const fetchMoviedata = (movieID) => {
      const sendpara = qs.stringify({ id: movieID });
      axios.get(localStorage.getItem("ip") + '/movielist/selectById', sendpara).then(
          function (response) {
            const movieInfo = response.data;
            movieData.value = {
              id: movieInfo.id,
              name: movieInfo.name,
              description: movieInfo.description,
              director: movieInfo.director,
              photo: movieInfo.photo,
              duration: movieInfo.str_duration,
              tag: movieInfo.tag,
              region: movieInfo.region
            };
          }
      );
    };


    const form = reactive({
      gender:"",
      old: "",
      new: "",
      email:"",
      desc: "请在此输入您的个人简介",
    });
    const onSubmit = () => {};

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
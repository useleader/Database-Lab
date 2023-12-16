import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import './assets/css/icon.css'


localStorage.setItem("ip", "http://localhost:8080/Database_Lab_war_exploded/");
const app = createApp(App)
installElementPlus(app)
app
    .use(store)
    .use(router)
    .mount('#app')
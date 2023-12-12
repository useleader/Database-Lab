import vue from '@vitejs/plugin-vue'

export default {
    base: './',
    plugins: [vue()],
    optimizeDeps: {
        include: ['schart.js']
    },
    // 解决跨域问题
// 关闭语法检查
    lintOnSave:false,
    devServer: {
        proxy: {
            '/api': {
                //后端接口的baseurl
                target: 'http://localhost:8080/Database_Lab_war_exploded/',
                //是否允许跨域
                changeOrigin: true,
                pathRewrite: {
                    //这里的作用是使用去掉api
                    '^/api': ''
                }
            }
        }
    }

}

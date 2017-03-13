// see http://vuejs-templates.github.io/webpack for documentation.
var path = require('path')

const dataURL = 'http://localhost:8088';//http://180.168.43.246:8897/'; //开发环境中数据服务器地址

module.exports = {
    build: {
        env: require('./prod.env'),
        index: path.resolve(__dirname, '../dist/index.html'),
        assetsRoot: path.resolve(__dirname, '../dist'),
        assetsSubDirectory: 'static',
        assetsPublicPath: '/',
        productionSourceMap: true,
        // Gzip off by default as many popular static hosts such as
        // Surge or Netlify already gzip all static assets for you.
        // Before setting to `true`, make sure to:
        // npm install --save-dev compression-webpack-plugin
        productionGzip: false,
        productionGzipExtensions: ['js', 'css']
    },
    dev: {
        env: require('./dev.env'),
        port: 8089,
        assetsSubDirectory: 'static',
        assetsPublicPath: '/',
        proxyTable: {            
            '/sapi': {
                target: dataURL,
                changeOrigin: true,
                pathRewrite: {
                    '^/sapi': 'sapi'
                }
            }   
        },
        // CSS Sourcemaps off by default because relative paths are "buggy"
        // with this option, according to the CSS-Loader README
        // (https://github.com/webpack/css-loader#sourcemaps)
        // In our experience, they generally work as expected,
        // just be aware of this issue when enabling this option.
        cssSourceMap: false
    }
}
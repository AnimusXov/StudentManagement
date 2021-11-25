import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'


const app = createApp(App);
app.component('Date-picker', Datepicker);
app.use(router).mount('#app');




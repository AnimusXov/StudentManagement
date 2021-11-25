<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Name</label>
        <input
            type="text"
            class="form-control"
            id="name"
            required
            v-model="student.name"
            name="name"
        />
      </div>

      <div class="form-group">
        <label for="surname">surname</label>
        <input
            class="form-control"
            id="surname"
            required
            v-model="student.surname"
            name="surname"
        />
      </div>


      <div class="form-group">
        <label for="email">Email</label>
        <input
            class="form-control"
            id="email"
            required
            v-model="student.email"
            name="email"
        />
      </div>

      <div class="form-group">
        <label >Date of Birth</label>
          <div>
            <Datepicker for=dob v-model="date"  textInput />
          </div>
      </div>

      <button @click="saveStudent" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newStudent">Add</button>
    </div>
  </div>
</template>

<script>
import StudentDataService from "../services/StudentDataService";
import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'
import { ref } from "vue";



export default {
  name: "add-student",
  components:{
    Datepicker
  },


  data() {

    return {
      setup() {
        const date = ref(new Date());

        return {
          date,
        };
      },
      student: {
        id: null,
        name: "",
        surname: "",
        email: "",
        dob: null,
      },
      submitted: false
    };
  },
  methods: {
    saveStudent() {
      let data = {
        name: this.student.name,
        surname: this.student.surname,
        email: this.student.email,
        dob: this.student.dob
      };



      StudentDataService.create(data)
          .then(response => {
            this.student.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
    },

    newStudent() {
      this.submitted = false;
      this.student = {};
    }
  }
};

</script>


<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>

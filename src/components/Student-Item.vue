<template>
  <div v-if="currentStudent" class="edit-form">
    <h4>Student</h4>
    <form>
      <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name"
               v-model="currentStudent.name"
        />
      </div>
      <div class="form-group">
        <label for="surname">Surname:</label>
        <input type="text" class="form-control" id="surname"
               v-model="currentStudent.surname"
        />
      </div>

      <div class="form-group">
        <label for="email">E-mail:</label>
        <input type="text" class="form-control" id="email"
               v-model="currentStudent.email"
        />
      </div>
      <div class="form-group">
        <label for=dob>Date of Birth</label>
        <div>
          <Datepicker
              v-model="currentStudent.dob"
              format="dd-MM-yyyy"
              name="dob"
              id="dob"
              type="date"
          />
        </div>
      </div>

    </form>

    <button class="badge badge-primary mr-2"
            v-if="currentStudent.email"
            @click="updateActivated(false)"
    >
      Deactivate
    </button>
    <button v-else class="badge badge-primary mr-2"
            @click="updateActivated(true)"
    >
      Activate
    </button>

    <button class="badge badge-danger mr-2"
            @click="deleteStudent"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
            @click="updateStudent"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Student...</p>
  </div>
</template>

<script>
import StudentDataService from "../services/StudentDataService";
import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'
import {ref} from "vue";


export default {
  name: "student-item",
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
      currentStudent: null,
      message: ''
    };
  },
  methods: {

    getStudent(id) {
      StudentDataService.get(id)
          .then(response => {
            this.currentStudent = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    updateActivated() {
      let data = {
        id: this.currentStudent.id,
        name: this.currentStudent.name,
        surname: this.currentStudent.surname,
        email: this.currentStudent.email,
        dob: this.currentStudent.dob,
      };

      StudentDataService.update(this.currentStudent.id, data)
          .then(response => {
            console.log(response.data);
            this.message = 'The status was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
    },

    updateStudent() {
      StudentDataService.update(this.currentStudent.id, this.currentStudent)
          .then(response => {
            console.log(response.data);
            this.message = 'The student was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
    },

    deleteStudent() {
      StudentDataService.delete(this.currentStudent.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({ name: "students" });
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.message = '';
    this.getStudent(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>

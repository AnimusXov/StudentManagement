<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by name"
               v-model="name"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
                  @click="searchname"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Students List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index === currentIndex }"
            v-for="(student, index) in students"
            :key="index"
            @click="setActivestudent(student, index)"
        >
          {{ student.name }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllstudents">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentstudent">
        <h4>student</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentstudent.name }}
        </div>
        <div>
          <label><strong>Surname:</strong></label> {{ currentstudent.surname }}
        </div>
        <div>
          <label><strong>E-mail:</strong></label> {{ currentstudent.email}}
        </div>
        <div>
          <label><strong>Date of birth:</strong></label> {{ currentstudent.dob}}
        </div>


        <router-link :to="'/students/' + currentstudent.id" class="badge badge-warning">Edit</router-link>
      </div>
      <div v-else>
        <br />
        <p>Please click on a student...</p>
      </div>
    </div>
  </div>
</template>

<script>
import studentDataService from "../services/StudentDataService";

export default {
  name: "students-list",
  data() {
    return {
      students: [],
      currentstudent: null,
      currentIndex: -1,
      name: ""
    };
  },
  methods: {
    retrievestudents() {
      studentDataService.getAll()
          .then(response => {
            this.students = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshList() {
      this.retrievestudents();
      this.currentstudent = null;
      this.currentIndex = -1;
    },

    setActivestudent(student, index) {
      this.currentstudent = student;
      this.currentIndex = student ? index : -1;
    },

    removeAllstudents() {
      studentDataService.deleteAll()
          .then(response => {
            console.log(response.data);
            this.refreshList();
          })
          .catch(e => {
            console.log(e);
          });
    },

    searchname() {
      studentDataService.findByname(this.name)
          .then(response => {
            this.students = response.data;
            this.setActivestudent(null);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.retrievestudents();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>

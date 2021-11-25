import { createWebHistory, createRouter } from "vue-router";

const routes =  [
    {
        path: "/",
        alias: "/students",
        name: "students",
        component: () => import("./components/StudentsList")
    },
    {
        path: "/students/:id",
        name: "student-details",
        component: () => import("./components/Student-Item")
    },
    {
        path: "/add",
        name: "add",
        component: () => import("./components/AddStudent")
    }
    ];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
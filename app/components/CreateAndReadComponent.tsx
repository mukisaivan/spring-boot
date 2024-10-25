"use client"

import React, { useState, useEffect } from "react"
import axios from "axios"
import { EmployeeType } from "../types/EmployeeType"

export default function CreateAndReadComponent() {
  const [employees, setEmployees] = useState<EmployeeType[]>([])
  const [firstName, setFirstName] = useState("")
  const [lastName, setLastName] = useState("")
  const [dateOfBirth, setDateOfBirth] = useState("")

  useEffect(() => {
    fetchEmployees()
  }, [])

  const fetchEmployees = async () => {
    try {
      const response = await axios.get("http://localhost:5000/employees")

      console.log("************ " + response.data)

      setEmployees(response.data)
    } catch (error) {
      console.error("Error fetching employees:", error)
    }
  }

  const addEmployee = async () => {
    try {
      if (firstName != null || lastName != null || dateOfBirth != null) {
        const newEmployee = { firstName, lastName, dateOfBirth }
        await axios.post("http://localhost:5000/employees", newEmployee)
      } else {
        alert("fill all fields")
      }
      fetchEmployees() // Refresh the list after adding a new employee
      setFirstName("")
      setLastName("")
      setDateOfBirth("")
    } catch (error) {
      console.error("Error adding employee:", error)
    }
  }

  const handleDeleteEmployee = async (id: number) => {
    await axios.delete(`http://localhost:5000/employees/${id}`)
    fetchEmployees()
  }

  return (
    <div className=" flex flex-col gap-4 max-w-screen-sm p-5">
      <h1>Employee Management</h1>

      <h2>Add Employee</h2>
      <input
        type="text"
        placeholder="First Name"
        value={firstName}
        onChange={(e) => setFirstName(e.target.value)}
      />
      <input
        type="text"
        placeholder="Last Name"
        value={lastName}
        onChange={(e) => setLastName(e.target.value)}
      />
      <input
        type="date"
        className=" text-black"
        placeholder="Date of Birth"
        value={dateOfBirth}
        onChange={(e) => setDateOfBirth(e.target.value)}
      />
      <button onClick={addEmployee} className=" bg-red-600">
        Add Employee
      </button>

      <h2>Employee List</h2>
      <div>
        {employees.map((employee) => (
          <div key={employee.id}>
            <li
              key={employee.id}
              className=" flex justify-between bg-red-300 m-2 rounded-full relative p-2 hover:bg-red-600 duration-500 transform cursor-pointer"
            >
              <div className=" w-[1/2]">
                {employee.firstName} {employee.lastName} -{" "}
                {employee.dateOfBirth}
              </div>
              <div
                onClick={() => handleDeleteEmployee(employee.id)}
                className="bg-white m-1 cursor-pointer"
              >
                ❌
              </div>
            </li>
          </div>
        ))}
      </div>
    </div>
  )
}

import React, { useState, useEffect } from "react"
import axios from "axios"
import { EmployeeType } from "../types/EmployeeType"

function App() {
  const [employees, setEmployees] = useState<EmployeeType[] | []>([])
  const [employeeDetails, setEmployeeDetails] = useState({
    firstName: "",
    lastName: "",
    dateOfBirth: "",
  })
  const [updateId, setUpdateId] = useState<number | null>(null)

  useEffect(() => {
    fetchEmployees()
  }, [])

  const fetchEmployees = async () => {
    const response = await axios.get("/employees")
    setEmployees(response.data)
  }

  const handleAddEmployee = async () => {
    await axios.post("/employees", employeeDetails)
    fetchEmployees()
  }

  const handleUpdateEmployee = async (id: number) => {
    await axios.put(`/employees/${id}`, employeeDetails)
    fetchEmployees()
  }

  const handleDeleteEmployee = async (id: number) => {
    await axios.delete(`/employees/${id}`)
    fetchEmployees()
  }

  const handleEditClick = (employee: EmployeeType) => {
    setEmployeeDetails({
      firstName: employee.firstName,
      lastName: employee.lastName,
      dateOfBirth: employee.dateOfBirth,
    })
    setUpdateId(employee.id)
  }

  return (
    <div>
      <h1>Employee Management</h1>

      <div>
        <input
          type="text"
          placeholder="First Name"
          value={employeeDetails.firstName}
          onChange={(e) =>
            setEmployeeDetails({
              ...employeeDetails,
              firstName: e.target.value,
            })
          }
        />
        <input
          type="text"
          placeholder="Last Name"
          value={employeeDetails.lastName}
          onChange={(e) =>
            setEmployeeDetails({ ...employeeDetails, lastName: e.target.value })
          }
        />
        <input
          type="date"
          placeholder="Date of Birth"
          value={employeeDetails.dateOfBirth}
          onChange={(e) =>
            setEmployeeDetails({
              ...employeeDetails,
              dateOfBirth: e.target.value,
            })
          }
        />
        <button
          onClick={() =>
            updateId ? handleUpdateEmployee(updateId) : handleAddEmployee()
          }
        >
          {updateId ? "Update Employee" : "Add Employee"}
        </button>
      </div>

      <h2>Employee List</h2>
      <ul>
        {employees.map((employee) => (
          <li key={employee.id}>
            {employee.firstName} {employee.lastName} - {employee.dateOfBirth}
            <button onClick={() => handleEditClick(employee)}>Edit</button>
            <button onClick={() => handleDeleteEmployee(employee.id)}>
              Delete
            </button>
          </li>
        ))}
      </ul>
    </div>
  )
}

export default App

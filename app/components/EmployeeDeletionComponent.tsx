import React, { useState } from "react"
import axios from "axios"

function EmployeeDelete() {
  const [employee, setEmployee] = useState({
    id: 1,
    firstName: "John",
    lastName: "Doe",
  })
  const [ids, setIds] = useState([1, 2, 3])

  const deleteEmployee = async () => {
    await axios.delete("/employees", { data: employee })
  }

  const deleteEmployeesByIds = async () => {
    await axios.delete("/employees/deletebyids", { data: ids })
  }

  const deleteMultipleEmployees = async () => {
    const employees = [
      { id: 1, firstName: "John", lastName: "Doe" },
      { id: 2, firstName: "Jane", lastName: "Doe" },
    ]
    await axios.delete("/employees/delete", { data: employees })
  }

  return (
    <div>
      <button onClick={deleteEmployee}>Delete Single Employee</button>
      <button onClick={deleteEmployeesByIds}>Delete Employees by ID</button>
      <button onClick={deleteMultipleEmployees}>
        Delete Multiple Employees
      </button>
    </div>
  )
}

export default EmployeeDelete

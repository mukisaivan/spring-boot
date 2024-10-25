import React, { useState, useEffect, ChangeEvent } from "react"
import axios from "axios"
import { useParams } from "next/navigation"

const UpdateEmployee = () => {
  const { id } = useParams() // Assume ID is passed as a route parameter
  const [employee, setEmployee] = useState({
    firstName: "",
    lastName: "",
    dateOfBirth: "",
  })

  useEffect(() => {
    // Fetch existing employee data
    axios
      .get(`http://localhost:5000/employees/${id}`)
      .then((response) => {
        setEmployee(response.data)
      })
      .catch((error) => {
        console.log("Error fetching employee data:", error)
      })
  }, [id])

  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setEmployee({ ...employee, [name]: value })
  }

  const handleUpdate = () => {
    axios
      .put(`http://localhost:5000/employees/${id}`, employee)
      .then((response) => {
        alert("Employee updated successfully!")
      })
      .catch((error) => {
        console.log("Error updating employee:", error)
      })
  }

  return (
    <div>
      <h2>Update Employee</h2>
      <form>
        <div>
          <label>First Name:</label>
          <input
            type="text"
            name="firstName"
            value={employee.firstName}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Last Name:</label>
          <input
            type="text"
            name="lastName"
            value={employee.lastName}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Date of Birth:</label>
          <input
            type="date"
            name="dateOfBirth"
            value={employee.dateOfBirth}
            onChange={handleInputChange}
          />
        </div>
        <button type="button" onClick={handleUpdate}>
          Update Employee
        </button>
      </form>
    </div>
  )
}

export default UpdateEmployee

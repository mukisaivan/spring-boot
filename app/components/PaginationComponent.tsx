import React, { useState, useEffect } from "react"
import axios from "axios"
import { EmployeeType } from "../types/EmployeeType"

const EmployeeList = () => {
  const [employees, setEmployees] = useState<EmployeeType[]>([])
  const [page, setPage] = useState(0)
  const [size, setSize] = useState(10)
  const [totalPages, setTotalPages] = useState(0)

  useEffect(() => {
    fetchEmployees()
  }, [page, size])

  const fetchEmployees = async () => {
    try {
      const response = await axios.get(
        `http://localhost:5000/paginatedemployees?page=${page}&size=${size}&sortBy=firstName`
      )
      setEmployees(response.data.content)
      setTotalPages(response.data.totalPages)
    } catch (error) {
      console.error("Error fetching employees:", error)
    }
  }

  const handleNextPage = () => {
    if (page < totalPages - 1) setPage(page + 1)
  }

  const handlePreviousPage = () => {
    if (page > 0) setPage(page - 1)
  }

  return (
    <div>
      <h2>Employee List</h2>
      <ul>
        {employees.map((employee) => (
          <li key={employee.id}>
            {employee.firstName} {employee.lastName} - {employee.dateOfBirth}
          </li>
        ))}
      </ul>
      <button onClick={handlePreviousPage} disabled={page === 0}>
        Previous
      </button>
      <button onClick={handleNextPage} disabled={page === totalPages - 1}>
        Next
      </button>
    </div>
  )
}

export default EmployeeList

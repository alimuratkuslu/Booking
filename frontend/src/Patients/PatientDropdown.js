import React, { useState, useEffect } from "react";
import axios from "axios";

function PatientDropdown() {
  // State to store the list of objects
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    axios
      .get("/patient")
      .then(response => setPatients(response.data))
      .catch(error => console.log(error));
  }, []);

  const [selectedEntity, setSelectedEntity] = useState(null);

  const handleEntitySelection = (event) => {
    setSelectedEntity(patients.find((e) => e.name === event.target.value));
  };

  return (
    <div>
      <select onChange={handleEntitySelection}>
        {patients.map((e) => (
          <option key={e.name} value={e.name}>
            {e.name} {e.surname}
          </option>
        ))}
      </select>
      {selectedEntity ? (
        <div>
          <h2>Attributes of Patients</h2>
          <p>{selectedEntity.name}</p>
          <p>{selectedEntity.surname}</p>
          <p>{selectedEntity.email}</p>
          <p>{selectedEntity.birthDate}</p>
        </div>
      ) : (
        <div>Please select a patient</div>
      )}
    </div>
  );
}

export default PatientDropdown;


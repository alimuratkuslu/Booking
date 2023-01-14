import React, { useState, useEffect } from "react";
import axios from "axios";

function DoctorDropdown() {
  // State to store the list of objects
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    axios
      .get("/doctor")
      .then(response => setDoctors(response.data))
      .catch(error => console.log(error));
  }, []);

  const [selectedEntity, setSelectedEntity] = useState(null);

  const handleEntitySelection = (event) => {
    setSelectedEntity(doctors.find((e) => e.name === event.target.value));
  };

  return (
    <div>
      <select onChange={handleEntitySelection}>
        {doctors.map((e) => (
          <option key={e.name} value={e.name}>
            {e.name} {e.surname}
          </option>
        ))}
      </select>
      {selectedEntity ? (
        <div>
          <h2>Attributes of Doctor</h2>
          <p>{selectedEntity.name}</p>
          <p>{selectedEntity.surname}</p>
          <p>{selectedEntity.email}</p>
          <p>{selectedEntity.field}</p>
        </div>
      ) : (
        <div>Please select a doctor</div>
      )}
    </div>
  );
}

export default DoctorDropdown;


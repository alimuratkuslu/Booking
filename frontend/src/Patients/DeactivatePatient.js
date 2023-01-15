import React, { useState } from "react";
import axios from "axios";

const DeactivatePatient = ({ patientId }) => {
  const [isDeactivating, setIsDeactivating] = useState(false);

  const handleDeactivate = async () => {
    setIsDeactivating(true);
    try {
        const response = await axios.patch(`/patient/${patientId}`);
    
        if (response.status === 200) {
          alert("Patient deactivated successfully!");
        } else {
          alert("There was an error deactivating the patient.");
        }
    } catch (error) {
      console.error(error);
      alert("There was an error deactivating the patient.");
    } finally {
      setIsDeactivating(false);
    }
  };

  return (
    <button
      disabled={isDeactivating}
      onClick={handleDeactivate}
    >
      Deactivate
    </button>
  );
};

export default DeactivatePatient;

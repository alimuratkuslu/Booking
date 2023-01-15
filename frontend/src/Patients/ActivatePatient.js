import React, { useState } from "react";
import axios from "axios";

const ActivatePatient = ({ patientId }) => {
  const [isDeactivating, setIsDeactivating] = useState(false);

  const handleDeactivate = async () => {
    setIsDeactivating(true);
    try {
        const response = await axios.patch(`/patient/activate/${patientId}`);
    
        if (response.status === 200) {
          alert("Patient activated successfully!");
        } else {
          alert("There was an error activating the patient.");
        }
    } catch (error) {
      console.error(error);
      alert("There was an error activating the patient.");
    } finally {
      setIsDeactivating(false);
    }
  };

  return (
    <button
      disabled={isDeactivating}
      onClick={handleDeactivate}
    >
      Activate
    </button>
  );
};

export default ActivatePatient;

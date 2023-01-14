import React, { useState } from 'react';

export default function Checkbox({ value }) {
  return (
    <div>
    { value ? 
      <div style={{width: '20px', height: '20px', background: 'green', border: '1px solid black'}}> </div> : 
      <div style={{width: '20px', height: '20px', background: 'red', border: '1px solid black'}}> </div>
    }
    </div>
  );
}


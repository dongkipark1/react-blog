import axios from "axios";
import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const JoinForm = (props) => {

  const navigate = useNavigate();

  const [user, setUser] = useState({
    username: "",
    password: "",
    email: "",
  });

  function changeValue(e){
    setUser({
      ...user,
      [e.target.name]: e.target.value
    });
  }

  async function submitJoin(error){
    error.preventDefault(); // 새로고침 막기 (action 발동 막기)

    // 유효성 검사

    try {
      await axios({
        url: "http://localhost:8080/join",
        method: "post",
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        },
        data: user 
      });
      navigate("/loginForm");
    } catch (error) {
      console.log(error);
      alert(error.response.data.msg);
    }
    
    // if(response.status === 200){ // 타입 까지 확인 하려면 =을 3개 붙여야 함
      
    // }else{
    //   alert("회원가입 실패")
    // }
  }

  return (
    <Form>
      <Form.Group>
        <Form.Label>Username</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter username"
          name="username"
          onChange={changeValue}
        />
      </Form.Group>

      <Form.Group>
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Enter password"
          name="password"
          onChange={changeValue}
        />
      </Form.Group>

      <Form.Group>
        <Form.Label>Email</Form.Label>
        <Form.Control
          type="email"
          placeholder="Enter email"
          name="email"
          onChange={changeValue}
        />
      </Form.Group>
      <Button variant="primary" type="submit" onClick={submitJoin}>
        회원가입
      </Button>
    </Form>
  );
};

export default JoinForm;
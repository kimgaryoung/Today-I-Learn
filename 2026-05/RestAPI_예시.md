
회사,팀마다 다를 수 있다고 하셨습니다. 
배운 내용을 정리하는 용도 입니다. 


https://github.com/kimgaryoung/Today-I-Learn/edit/main/2026-05/



## 핵심 규칙

### 1. URL은 자원(Resource)을 표현한다.
### 2. 자원에 대한 행위는 HTTP 메서드로 표현한다.





1) 회원을 생성해주세요 

Post /users


2) 코난이라는 이름을 가진 강아지 정보를 생성한다. 

코난 : 메세지 
강아지 :리소스 
생성한다 : 메소드 

Post http://mypet/pets
{
    "pets":{
      "name":"코난"
      }

}


3) 회원 가입할 떄 

Post /user


4) 로그인 할떄 
Post /auth

로그인 결과 =인증 정보 얻음 
인정 정보 = /auth
생성 = Post



5) 회원 삭제 : 복구 약관 30일 이내는 복구 해명 

Delete /usesrs/{uer_id} (o)

Patch/usesrs/{user_id} (x)



6) charile회원의 이름을 변경하세요
PATCH /user/charile{"name" :"Charlie"}


프론트엔드 개발자 입장에서 서버를 기준으로 생각할 필요가 없다. patch를 사용하는 경우 추가적인 설명이 필요할 수 있어 오히려 번거로운 것 같다. 





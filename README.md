# triple_backend_api

---


## 1. 어플리케이션 실행 방법 

    1. 소스코드 다운받기 
    2. pointApi/src/main/resources/applications.properties 파일 DB 정보 세팅 --> MySQL 8.0 이상버전 
    3. DDL 파일 실행 
    4. 스프링부트 어플리케이션 실행 
    
    
    
## 2. API 

    1. point 조회 
      
      mysql 접속하여, 
      
      User 테이블에 사용자 Entity 생성 
      Place 테이블에 장소 Entity 생성
      
      
      --   insert into user(user_id, account, password, point) values( unhex(replace(uuid(),'-','')),  "isak", "test", 1 ); 
        
      --   insert into place(place_id, place_name) values(unhex(replace(uuid(),'-','')),"연남동");
      
  
      localhost:8080/{account}/point 
      
      사용자의 계정정보를 파라미터로 받아 사용자 포인트 조회 
      
    
    2. point 적립 
       
       포스트맨을 이용해서,  
       
       localhost:8080/review 에 
       
       ex) 
       
       {
        "type": "REVIEW",
        "action": "ADD", /* "MOD", "DELETE" */
        "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
         "content": "좋아요!",
          "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-
              851d-4a50-bb07-9cc15cbdc332"],
          "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
          "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f" 
       }
     
        post 요청 
        
     
     

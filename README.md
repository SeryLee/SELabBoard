## 2022-10-14
## 에스이랩 게시판

        목표
        1. 자바스크립트 안쓰기
        2. 엔티티 
        3. 디비 인덱스 +++

### 1. 회원가입
#### 1.1 희망기능 자바스크립트 없이 바로 검증(validation)
#### 1.2 서버내부에 검증 중복검사 
#### 1.3 유저 권한. enum
유저 
id -> pk seq
username -> 이메일
password -> 4자리 이상 -> 암호화
이름
주소

### 2. 로그인 cookie -> session
인증 인가

https://www.youtube.com/watch?v=JZgD8aPkHSc

https://www.youtube.com/watch?v=y0xMXlOAfss

1 -> n
유저 엔티티 -> 게시판 엮는다.
ddl -> create 

### 3. 게시판 생성
- 게시판
* 제목
* 작성자id
* 내용
* 날짜
* ++++ 첨부파일, 이나 이미지 추가기능
* 검증
#### CRUD +++ 페이징 추가기능

4순위 댓글 -> fk 작성자id, 게시글id, if(대댓글) 댓글 id

---
## 2022-10-24
## 게시판 CRUD 완성 및 이후 과제

### 1. update write 분리 
### 1-2 entity -> dto -> spring validation
### 2. request mapping 정리 get,post....
### 3. 회원가입 -> cookie -> session


## CRUD 생성
- 유저 회원가입
- 유저 로그인
- 유저 업데이트
- 유저 조회(관리자)
## JWT + Security 적용
- JWT secret key yml 파일 저장
- 로그인시 Access Token, Refresh  Token 생성
- Token Payload 커스텀
  - sub : 유저 id
  - email : 유저 이메일
  - role : 유저 권한
  - iat : 토큰 생성 시각
  - exp : 토큰 만료 시각
- 유저 role 별 API 권한 체크
  - ROLE_USER
  - ROLE_ADMIN
- Filter 토큰 검증후 request에 User 객체 Attribute 전달
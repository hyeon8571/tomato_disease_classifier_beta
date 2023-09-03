# Tomato Disease Classifier V2

<hr/> 
Densenet + Flask + SpringBoot를 활용한 토마토 잎 질병 분류 웹

5장의 질병관련 이미지를 업로드 하면 이미지 분석 인공지능을 활용하여 질병명/해결방안, 정확률을 알려준다.

<hr>

### 구현기능
- 파일 업로드(jpg, jpeg, png)
- 이미지 분석
- 결과 및 해결방안 제시
- 회원가입
- 로그인
- 게시글
  - 리스트 조회
  - 상세보기
  - 작성
  - 수정
  - 삭제
- 댓글
  - 작성
  - 수정
  - 삭제

### V1 -> V2 변화
- Spring Security 적용
- Jwt Token 활용
- Resp Api
- Custom Exception 사용

### 추가 예정 기능
- 아이디/비밀번호 찾기
- 마이페이지 
- 게시글 추천/비추천
- 페이징


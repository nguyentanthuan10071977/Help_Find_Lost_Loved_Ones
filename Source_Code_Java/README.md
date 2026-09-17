# HFLLO – Help Find Lost Loved Ones REST API

Spring Boot 3 + Java 17 + Spring Web + Spring Data JPA + H2.

## Chạy project
```bash
mvn spring-boot:run
```
API: `http://localhost:8080`

## Các nhóm endpoint
- Posts: `GET/POST /api/posts`, `GET/PUT/DELETE /api/posts/{id}`
- Search post: `/api/posts/search?keyword=...`
- Advanced search: `/api/posts/advanced?age=30&year=2010&circumstance=...`
- Lost time: `/api/posts/lost-time?from=2000&to=2010`
- Lost circumstance: `/api/posts/lost-circumstance?value=...`
- Search by object: `/api/posts/by-object?value=...`
- Comment: `POST/GET /api/posts/{id}/comments`
- Find relative registration: `POST /api/find-relatives`
- Admin review: `GET /api/find-relatives`, `PUT /api/find-relatives/{id}/status?value=APPROVED`
- News: CRUD `/api/news`, search `/api/news/search?keyword=...`
- Video media: CRUD `/api/videos`
- Search information: CRUD `/api/search-information`
- Chat with admin: `GET/POST /api/chat/{userId}`
- Register/login: `POST /api/auth/register`, `POST /api/auth/login`

## Ví dụ tạo post
```json
{
  "title": "Tìm người thân",
  "content": "Thông tin cần tìm...",
  "lostPersonName": "Nguyen Van A",
  "lostCircumstance": "Mất liên lạc",
  "lostYear": 2010,
  "lostAge": 25,
  "objectInformation": "Đồng hồ màu bạc"
}
```

## Ghi chú
File User Stories mô tả chức năng và acceptance criteria, không quy định database schema, JWT, công nghệ lưu ảnh, AI nhận diện ảnh hay API Zalo. Vì vậy project này cung cấp phần REST API nền tảng tương ứng; các phần AI/image-recognition, lưu file thật và tích hợp Zalo cần triển khai thêm theo đặc tả kỹ thuật của nhóm.

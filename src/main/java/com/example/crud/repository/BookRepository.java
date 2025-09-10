package com.example.crud.repository;

import com.example.crud.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    /**
     * Tự định nghĩa một query để tìm kiếm sách.
     * @Query annotation nhận vào một chuỗi là câu lệnh truy vấn của MongoDB.
     *
     * Cú pháp truy vấn:
     * - {'name': ...}: Tìm kiếm trong trường 'name'.
     * - { $regex: ?0 }: Sử dụng biểu thức chính quy (regular expression) để khớp một phần.
     *   - ?0 là một placeholder, nó sẽ được thay thế bằng tham số đầu tiên của phương thức (ở đây là 'keyword').
     * - { $options: 'i' }: 'i' là một tùy chọn cho regex, có nghĩa là case-insensitive (không phân biệt hoa-thường).
     */
    @Query("{'name': { $regex: ?0, $options: 'i' }}")
    List<Book> searchByName(String keyword);
}

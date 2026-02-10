Refleksi 1

Prinsip Clean Code yang saya terapkan dalam mengerjakan fitur Edit dan Delete Product, saya melakukan beberapa prinsip Clean Code agar codenya mudah dibaca dan digunakan kembali oleh orang lain:
1. Nama dari sebuah variable harus jelas dan punya makna (Variable)
   
   Saya menggunakan nama variabel dan method yang jelas fungsinya, seperti create, edit, delete, findById, dan productRepository.
   Dengan penamaan ini, kodingan jadi lebih mudah dipahami tanpa butuh banyak komentar (self-explanatory). Contohnya, saya menggunakan productid atau productName daripada sekadar variabel satu huruf seperti pName, p, name, pid dan sebagainya. 
2. Function (Fokus pada satu hal)
   
   Setiap method yang saya buat fokus pada satu tugas saja. Method delete hanya bertugas menghapus, dan method edit hanya bertugas memperbarui data. Logika untuk pindah halaman (redirection) tetap saya taruh di Controller, bukan di Service.    
3. Separation of Concerns (Sebenarnya di module 1nya emng udah dipisah)
   
Module 1 memisahkan kodingan sesuai arsitektur MVC (Model-View-Controller). Controller hanya bertugas menangani request dari user, Service untuk logika bisnis, dan Repository untuk manipulasi data.
Pemisahan ini membuat kodingan tidak menumpuk di satu file sehingga lebih mudah untuk di-maintain atau diperbaiki jika ada salah satu bagian yang bermasalah.

Oh iya, tambahan disini saya menambahkan UUID. hal ini sangat penting untuk mencegah serangan ID Enumeration, di mana orang asing bisa menebak-nebak ID barang lain dengan mudah hanya dengan mengganti angka di URL. Dengan UUID yang berupa kode acak panjang, ID hampir mustahil untuk ditebak.
(Untuk fitur edit dan delete)

Buat masalahnnya ini mungkin akan berkaitan dengan exersice 2 jadi nanti saya lanjutkan

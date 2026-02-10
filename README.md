Refleksi 1

Prinsip Clean Code yang saya terapkan dalam mengerjakan fitur Edit dan Delete Product, saya melakukan beberapa prinsip Clean Code agar codenya mudah dibaca dan digunakan kembali oleh orang lain:

Nama dari sebuah variable harus jelas dan punya makna (Variable)

Saya menggunakan nama variabel dan method yang jelas fungsinya, seperti create, edit, delete, findById, dan productRepository.
Dengan penamaan ini, kodingan jadi lebih mudah dipahami tanpa butuh banyak komentar (self-explanatory). Contohnya, saya menggunakan productid atau productName daripada sekadar variabel satu huruf seperti pName, p, name, pid dan sebagainya.

Function (Fokus pada satu hal)

Setiap method yang saya buat fokus pada satu tugas saja. Method delete hanya bertugas menghapus, dan method edit hanya bertugas memperbarui data. Logika untuk pindah halaman (redirection) tetap saya taruh di Controller, bukan di Service.

Separation of Concerns (Sebenarnya di module 1nya emng udah dipisah)

Module 1 memisahkan kodingan sesuai arsitektur MVC (Model-View-Controller). Controller hanya bertugas menangani request dari user, Service untuk logika bisnis, dan Repository untuk manipulasi data.
Pemisahan ini membuat kodingan tidak menumpuk di satu file sehingga lebih mudah untuk di-maintain atau diperbaiki jika ada salah satu bagian yang bermasalah.

Oh iya, tambahan disini saya menambahkan UUID. hal ini sangat penting untuk mencegah serangan ID Enumeration, di mana orang asing bisa menebak-nebak ID barang lain dengan mudah hanya dengan mengganti angka di URL. Dengan UUID yang berupa kode acak panjang, ID hampir mustahil untuk ditebak.
(Untuk fitur edit dan delete)

Buat masalahnnya ini mungkin akan berkaitan dengan exersice 2 jadi nanti saya lanjutkan

Refleksi 2

Perasaan saya setelah membuat unit test dan code coverage

Setelah menulis unit test, saya menyadari bahwa ternyata masih banyak kesalahan (bug) dalam kode yang saya buat sebelumnya. Misalnya, masalah perbedaan nama variabel yang tidak konsisten atau kesalahan logika kecil lainnya yang tidak terlihat saat hanya menulis kode fitur. Dengan adanya unit test, kesalahan-kesalahan tersebut bisa terdeteksi lebih dini sebelum aplikasi dijalankan secara keseluruhan. Hal ini membuat saya merasa lebih aman karena unit test bertindak sebagai "jaring pengaman" untuk memastikan setiap bagian kode berjalan sesuai ekspektasi.

Berapa banyak unit test dalam satu class?

Tidak ada angka pasti, namun jumlahnya harus cukup untuk mencakup semua jalur logika (logical paths), termasuk skenario positif, skenario negatif, dan edge cases (kondisi batas).

Bagaimana memastikan unit test sudah cukup?

Kita bisa menggunakan metrik Code Coverage. Ini adalah ukuran seberapa banyak baris kode kita yang sudah dieksekusi oleh test. Di IntelliJ, kita bisa melihat ini melalui fitur "Run with Coverage".

Apakah 100% Code Coverage berarti 0 Bug?

Tidak. Code coverage hanya menjamin bahwa baris kode tersebut pernah dijalankan saat tes, bukan berarti logikanya sudah benar untuk semua kemungkinan input atau interaksi sistem yang kompleks. Bug tetap bisa muncul pada level integrasi atau karena logika bisnis yang salah dipahami.

Kebersihan Kode pada Functional Test Baru

Jika saya membuat functional test suite baru (misal untuk mengecek jumlah item di list) dengan menduplikasi setup dan variabel dari CreateProductFunctionalTest.java, maka kualitas kode tersebut akan menurun.

Isu Clean Code (Code Smells): Masalah utamanya adalah Code Duplication (Duplikasi Kode). Menuliskan prosedur setup yang sama (seperti inisialisasi driver, server port, dan base URL) berulang kali melanggar prinsip DRY (Don't Repeat Yourself).

Dampak: Kode menjadi sulit di-maintain. Jika di masa depan ada perubahan pada konfigurasi sistem (misalnya perubahan URL dasar atau penambahan setup driver), saya harus mengubahnya di banyak file sekaligus. Ini meningkatkan risiko kesalahan manusia.

Saran Perbaikan:

Base Class (Inheritance): Membuat sebuah base class (misal BaseFunctionalTest) yang berisi semua variabel instans dan prosedur @BeforeEach yang umum. Test class lain cukup melakukan extends ke base class tersebut.

Setup Methods: Membungkus logika navigasi atau interaksi yang sering digunakan ke dalam method pembantu (helper methods) atau menggunakan pola Page Object Model (POM) untuk memisahkan logika tes dengan detail elemen UI.

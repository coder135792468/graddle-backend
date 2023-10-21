insert into note(id,uid,title,desc,qualification,author,file,subject,download,approved)
values (
    1001,
    '2390487293847',
    'Answer/Questions Notes for 10th Class English.',
    'English notes for 10th Standard.',
    '10th',
    'Hamzatauqr',
    'https://firebasestorage.googleapis.com/v0/b/notes-share-3ba29.appspot.com/o/files%2Fdocument.pdf?alt=media&token=f4cc093b-8b21-43fb-b94c-e6c0e2628ecc',
    'Science',
     0,
     true
);

insert into note(id,uid,title,desc,qualification,author,file,subject,download,approved)
values (
    1002,
    '102339205015389215524',
    'Maths Notes',
    'Some interesting question on math you must try!!!',
    '11th',
    'Rahul',
    'https://firebasestorage.googleapis.com/v0/b/notes-share-3ba29.appspot.com/o/files%2Fmaths%20W1-4.pdf?alt=media&token=16992d41-cfb2-4fcc-9e1a-d95065832fc9',
    'Math',
     1,
     true
);

insert into note(id,uid,title,desc,qualification,author,file,subject,download,approved)
values (
    1003,
    '102339205015389215524',
    'BSC Physics Notes',
    'Checkout these awesome notes',
    'BSC',
    'Anshika',
    'https://firebasestorage.googleapis.com/v0/b/notes-share-3ba29.appspot.com/o/files%2Fmaths%20W1-4.pdf?alt=media&token=16992d41-cfb2-4fcc-9e1a-d95065832fc9',
    'BSC',
     1,
     true
);

insert into note(id,uid,title,desc,qualification,author,file,subject,download,approved)
values (
    1004,
    '102339205015389215524',
    'Stats Notes',
    'Wanna try some interesting questions?',
    'BS Data science',
    'Ashutosh Agrawal',
    'https://firebasestorage.googleapis.com/v0/b/notes-share-3ba29.appspot.com/o/files%2Fmaths%20W1-4.pdf?alt=media&token=16992d41-cfb2-4fcc-9e1a-d95065832fc9',
    'Stats',
     1,
     true
);

insert into library(id,uid,note_id)
values (100001, '2390487293847',1003)
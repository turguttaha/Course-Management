

create table course (
                        id bigint generated by default as identity,
                        description varchar(255),
                        name varchar(255),
                        study_points integer,
                        primary key (id)
);


create table person (
                        id bigint generated by default as identity,
                        email varchar(255),
                        first_name varchar(255),
                        last_name varchar(255),
                        phone varchar(255),
                        primary key (id)
);

create table person_course (
                               course_id bigint not null,
                               person_id bigint not null
);


create table session (
                         id bigint generated by default as identity,
                         end_date_time date,
                         start_date_time date,
                         course_id bigint,
                         primary key (id)
)
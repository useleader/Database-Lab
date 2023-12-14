alter table comment add constraint fk_film_comment
    foreign key (film_id) references film(film_id) on delete cascade;

alter table comment add constraint fk_user_comment
    foreign key (user_id) references user(user_id) on delete cascade;




alter table score add constraint fk_film_score
    foreign key (film_id) references film(film_id) on delete cascade;

alter table score add constraint fk_user_score
    foreign key (user_id) references user(user_id) on delete cascade;


alter table tag add constraint fk_film_tag
    foreign key (film_id) references film(film_id) on delete cascade;

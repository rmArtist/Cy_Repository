create or replace function fun_get_film_titles(p_year integer)
   returns text as $$
declare 
	 titles text default '';
	 rec_film   record;
	 cur_films cursor(p_year integer) 
		 for select title, release_year
		 from film
		 where release_year = p_year;
begin
   -- open the cursor
   open cur_films(p_year);
	
   loop
    -- fetch row into the film
      fetch cur_films into rec_film;
    -- exit when no more row to fetch
      exit when not found;

    -- build the output
      if rec_film.title like '%ful%' then 
         titles := titles || ',' || rec_film.title || ':' || rec_film.release_year;
      end if;
   end loop;
  
   -- close the cursor
   close cur_films;

   return titles;
end; $$

language plpgsql;

select * from fun_get_film_titles(2006);


select title, release_year
		 from film
		 where release_year = 2006 and title like '%ful%';
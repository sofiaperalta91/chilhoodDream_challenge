INSERT INTO train (id,amenities,description,distance_between_stop,grade_crossing,max_speed,name,sharing_tracks,train_frequency)
VALUES (nextval('train_id_seq'),'bar','Blue train','50m', true, '100km/h','Trainy',false,'Hourly'),
       (nextval('train_id_seq'),'bathroom','Red train','10km', true, '80km/h','Big eyes',false,'Hourly'),
       (nextval('train_id_seq'),'Electric plugs','Black train','30m', true, '70km/h','On the floor',false,'Hourly'),
       (nextval('train_id_seq'),'Space for bicycles','Graffitted train','300km', true, '150km/h','Bike train',false,'Hourly'),
       (nextval('train_id_seq'),'Food service','Flying train','50km', true, '200km/h','Star Wars',true,'Hourly');

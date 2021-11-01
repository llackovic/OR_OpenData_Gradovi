CREATE TABLE grad
(
	id SERIAL PRIMARY KEY,
	naziv varchar(100) NOT NULL UNIQUE,
	drzava varchar(50),
	kontinent varchar(50),
	povrsina numeric,
	nadmorska_visina smallint,
	broj_stanovnika int,
	gustoca_naseljenosti int,
	gradonacelnik varchar(50),
	web varchar(200),
	vremenska_zona smallint,
	glavni_grad boolean,
	
);

CREATE TABLE gradska_cetvrt 
(
	id SERIAL PRIMARY KEY,
	naziv varchar(100) NOT NULL UNIQUE,
	povrsina numeric,
	broj_stanovnika int,
	gustoca_naseljenosti int,
	grad_id int REFERENCES grad(id)
);

INSERT INTO grad (naziv, drzava, kontinent, povrsina, nadmorska_visina, broj_stanovnika, gustoca_naseljenosti, gradonacelnik, web, vremenska_zona, glavni_grad) VALUES
('Zagreb', 'Hrvatska', 'Europa', 641, 158, 790017, 1232, 'Tomislav Tomašević', 'http://www.zagreb.hr/', +01, true),
('Rijeka', 'Hrvatska', 'Europa', 44, 12, 128624, 2923, 'Marko Filipović', 'http://www.rijeka.hr/', +01, false),
('Split', 'Hrvatska', 'Europa', 79, 0, 178192, 2256, 'Ivica Puljak', 'http://www.split.hr/', +01, false),
('New York', 'Sjedinjene Američke Države', 'Sjeverna Amerika', 1223, 10, 8804190, 11314, 'Bill de Blasio', 'https://www.nyc.gov/', -05, false),
('Washington', 'Sjedinjene Američke Države', 'Sjeverna Amerika', 177, 125, 689545, 4361, 'Muriel Bowser', 'http://dc.gov/', -05, true),
('London', 'Ujedinjeno Kraljevstvo', 'Europa', 1572, 11, 8961989, 5701, 'Sadiq Khan', 'https://www.london.gov.uk/', +00, true),
('Barcelona', 'Španjolska', 'Europa', 101, 12, 1620343, 16043, 'Ada Colau Ballano', 'http://www.barcelona.cat/', +01, false),
('Berlin', 'Njemačka', 'Europa', 892, 34, 3664088, 4108, 'Michael Müller', 'http://www.berlin.de/en/', +01, true),
('Melbourne', 'Australija', 'Australija', 9992, 31, 5159211, 516, 'Sally Capp', 'https://www.melbourne.vic.gov.au/', +10, false),
('Rim', 'Italija', 'Europa', 1285, 21, 2860009, 2236, 'Roberto Gualtieri', 'https://www.comune.roma.it/', +01, true);

INSERT INTO gradska_cetvrt (naziv, povrsina, broj_stanovnika, gustoca_naseljenosti, grad_id) VALUES
('Sesvete', 165.26, 70633, 428, 1),
('Gornja Dubrava', 40.28, 62221, 1555, 1),
('Maksimir', 14.35, 49448, 3532, 1),
('Donji grad', 3.01, 37123, 12374, 1),
('Banderovo', 0.36, 2235, 6208, 2),
('Draga', 3.45, 1463, 424, 2),
('Podvežica', 0.73, 6110, 8369, 2),
('Bačvice', 1.19, 3347, 2812, 3),
('Bol', 0.52, 11550, 22212, 3),
('Lokve', 0.21, 7173, 34157, 3),
('The Bronx', 109.3, 1472654, 13482, 4),
('Brooklyn', 179.7, 2736074, 15227, 4),
('Manhattan', 58.8, 1694251, 28872, 4),
('Queens', 281.5, 2405464, 8542, 4),
('Columbia Heights', 2.2, 21376, 9716, 5),
('Georgetown', 3.035, 10829, 3568, 5),
('Southwark', 28.85, 318830, 11050, 6),
('City of Westminister', 21.48, 261317, 12170, 6),
('Ciutat Vella', 4.49, 111290, 24786, 7),
('Eixample', 7.46, 262485, 35586, 7),
('Sants-Montjuic', 21.35, 177636, 8321, 7),
('Charlottenburg-Wilmersdorf', 64.72, 319628, 4878, 8),
('Friedrichshain-Kreuzberg', 20.16, 268225, 13187, 8),
('Lichtenberg', 52.29, 259881, 4952, 8),
('Preston', 11.3, 32852, 2907, 9),
('Coburg', 7, 26185, 3740, 9),
('Clayton', 7.7, 19358, 2514, 9),
('Greensborough', 11.3, 20821, 1843, 9),
('Municipio I', 19.91, 186802, 9382, 10),
('Municipio II', 19.60, 167736, 8567, 10),
('Municipio X', 150.64, 230544, 1530, 10),
('Municipio XV', 186.70, 158561, 849, 10);


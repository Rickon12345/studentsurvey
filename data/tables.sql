-- Create a table for students
CREATE TABLE IF NOT EXISTS student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    status varchar(10) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_of_birth TIMESTAMP
);

-- Create a table for teachers
CREATE TABLE IF NOT EXISTS teacher (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(50) NOT NULL,
    status varchar(10) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_of_birth TIMESTAMP
);


-- Create a table for participant
CREATE TABLE IF NOT EXISTS participant (
    id INT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contact_number VARCHAR(255) NOT NULL,
    perc_effort VARCHAR(50) NOT NULL,
    attendance varchar(10) NOT NULL,
    perc_Academic varchar(10),
    CompleteYears varchar(10),
	House VARCHAR(50),
	survey_date TIMESTAMP
);


CREATE TABLE IF NOT EXISTS survey (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    participant_id INT,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (participant_id) REFERENCES participant(id),
	survey_date TIMESTAMP
);


-- Create a table for affiliation
CREATE TABLE IF NOT EXISTS affiliation (
    id INT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    nominationwave VARCHAR(255) NOT NULL,
    addtional1 VARCHAR(50) NOT NULL,
    addtional2 varchar(50) NOT NULL
);

-- Create a table for response
CREATE TABLE IF NOT EXISTS response (
    instance_id INT PRIMARY KEY,
    participant_id INT,
    status VARCHAR(50),
    manbox1 VARCHAR(50),
    manbox2 VARCHAR(50),
    manbox3 VARCHAR(50),
    manbox4 VARCHAR(50),
    manbox5 VARCHAR(50),
    isolated VARCHAR(50),
    womenDifferent VARCHAR(50),
    manbox5_overall VARCHAR(50),
    language VARCHAR(50),
    question5 VARCHAR(50),
    masculinity_contrained VARCHAR(50),
    growthMindset VARCHAR(50),
    COVID VARCHAR(50),
    criticises VARCHAR(50),
    menBetterSTEM VARCHAR(50),
    school_support_engage6 VARCHAR(50),
    pwi_wellbeing VARCHAR(50),
    intelligence1 VARCHAR(50),
    intelligence2 VARCHAR(50),
    soft VARCHAR(50),
    opinion VARCHAR(50),
    nerds VARCHAR(50),
    school_support_engage VARCHAR(50),
    comfortable VARCHAR(50),
    future VARCHAR(50),
    bullying VARCHAR(50),
    candidate_perc_effort VARCHAR(50),
    comments VARCHAR(500),
    FOREIGN KEY (participant_id) REFERENCES participant(id),
    survey_date TIMESTAMP
);

-- Create a table for friends
CREATE TABLE IF NOT EXISTS friends (
    participant_id INT,
    target INT,
	FOREIGN KEY (participant_id) REFERENCES participant(id),
    survey_date TIMESTAMP
);

-- Create a table for influential
CREATE TABLE IF NOT EXISTS influential (
    participant_id INT,
    target INT,
    FOREIGN KEY (participant_id) REFERENCES participant(id),
    survey_date TIMESTAMP
);

-- Create a table for feedback
CREATE TABLE IF NOT EXISTS feedback (
    participant_id INT,
    target INT,
    FOREIGN KEY (participant_id) REFERENCES participant(id),
    survey_date TIMESTAMP
);

-- Create a table for moretime
CREATE TABLE IF NOT EXISTS moretime (
    participant_id INT,
    target INT,
    FOREIGN KEY (participant_id) REFERENCES participant(id),
    survey_date TIMESTAMP
);

-- Create a table for advice
CREATE TABLE IF NOT EXISTS advice (
    participant_id INT,
    target INT,
    FOREIGN KEY (participant_id) REFERENCES participant(id),
    survey_date TIMESTAMP
);

-- Create a table for disrespect
CREATE TABLE IF NOT EXISTS disrespect (
    participant_id INT,
    target INT,
    FOREIGN KEY (participant_id) REFERENCES participant(id),
    survey_date TIMESTAMP
);

-- Create a table for SchoolActivityNet
CREATE TABLE IF NOT EXISTS SchoolActivityNet (
    participant_id INT,
    affiliation_id INT,
    FOREIGN KEY (participant_id) REFERENCES participant(id),
    FOREIGN KEY (affiliation_id) REFERENCES affiliation(id),
    survey_date TIMESTAMP
);

-- Create a table for nodesAnalytics
CREATE TABLE IF NOT EXISTS nodesAnalytics (
    node_Id INT,
    node_Label INT,
	node_in INT,
	node_out INT,
    in_degree_centrality VARCHAR(50),
    out_degree_centrality VARCHAR(50),
    eigenvector_centrality VARCHAR(50),
    betweenness_centrality VARCHAR(50),
    closeness_centrality VARCHAR(50),
    Clustering VARCHAR(50),
    nodeReciprocity VARCHAR(50),
    Community  VARCHAR(50),
    survey_date TIMESTAMP
);

-- Create a table for top10
CREATE TABLE IF NOT EXISTS top10 (
    node_Id INT PRIMARY KEY,
    node_Label INT,
    node_value VARCHAR(50),
    keyword_type VARCHAR(50),
    survey_date TIMESTAMP
);
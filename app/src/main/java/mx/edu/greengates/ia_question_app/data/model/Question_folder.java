package mx.edu.greengates.ia_question_app.data.model;

public class Question_folder {

    String[][] quizData = {
            {"topic_1_1","img: topic_1_1","5%","0.2%","2.5%","5%","10%","Solutions"},
            {"topic_1_2","img: topic_1_2","C","A","B","C","D","Solutions"},
            {"topic_1_3","img: topic_1_3","Coulomb","Mole","Kelvin","Coulomb","Ampere","Solutions"},
            {"topic_1_4","img: topic_1_4","15%","5%","25%","30%","15%","Solutions"},
            {"topic_2_1","img: topic_2_1","C","A","B","C","D","Solutions"},
            {"topic_2_2","img: topic_2_2","B","A","B","C","D","Solutions"},
            {"topic_2_3","img: topic_2_3","B","A","B","C","D","Solutions"},
            {"topic_2_4","img: topic_2_4","A","A","B","C","D","Solutions"},
            {"topic_3_1","img: topic_3_1","30MPa","10MPa","15MPa","30MPa","40MPa","Solutions"},
            {"topic_3_2","img: topic_3_2","B","A","B","C","D","Solutions"},
            {"topic_3_3","img: topic_3_3","400s,","250s","2500s","4000s","400s","Solutions"},
            {"topic_3_4","img: topic_3_4","120/m","60/m","120/m","180/m","240/m","Solutions"},
            {"topic_4_1","img: topic_4_1","D","A","B","C","D","Solutions"},
            {"topic_4_2","img: topic_4_2","B","A","B","C","D","Solutions"},
            {"topic_4_3","img: topic_4_3","D","A","B","C","D","Solutions"},
            {"topic_4_4","img: topic_4_4","D","A","B","C","D","Solutions"},

            {"trig_1","img: trig_1","5","3","4","7","5","consA = 0 → 36+64-a^2 = 0 → a = 10 → R = a/2sinA = 5"},
            {"trig_2","img: trig_2","Δ","s^2","ab+bc+ca","Δ","noe of these","sinA/a = sinB/b = sinC/ = 1/2R → sinA = a/2R. therefore 2R^2sinAsinBsinC = 2R^2(a/2R)・(b/2R)・(c/2R) = abc/4R = Δ"},
            {"trig_3","img: trig_3","nπ/2 + π/8","nπ + π/4","nπ/2 + π/8","nπ/2 + π/8","none of these","simplification it reduces to cos2θ = sin2θ → tan2θ = tanπ/4 → 2θ = nπ + π/4 → θ = nπ/2 + π/8"},
            {"trig_4","img: trig_4","nπ ± π/6","2nπ ± π/6","2nπ ± π/3","nπ ± π/3","nπ ± π/6","cos^2θ = 3/4 = cos^2(π/6) → θ = nπ ± π/6"},
            {"comp_1","img: comp_1","No value of x","x = nπ,x = (n + 1/2)π","x = nπ","x = 0","No value of x","img: comp_1_solution"},
            {"comp_2","img: comp_2","4","1","2","3","4","img: comp_2_solution"},
            {"comp_3","img: comp_3","1","1","-1","0","None of these","img: comp_3_solution"},
            {"comp_4","img: comp_4","(-7-26i)/25","(7-26i)/25","(-7-26)/25","(-7+26i)/25","(7+26i)/25","img: comp_4_solution"},
            {"vec_1","If 4i^+7j^+8k^ 2i^+3j^+4k^ and 2i^+5j^+7k^ are the position vectors of the vertices A B and C respectively of triangle ABC. Then the position vector of the point where the bisector of angle A meets BC is","1/3(6i+8j+6k)","2/3(-6i-8j-6k)","2/3(6i+8j+6k)","1/3(6i+8j+18k)","1/3(5j+12k)","img: vec_1_solution"},
            {"vec_2","If a satisfies a × (i + 2j + k) = i - k. Then a is equal to ","λi + (2λ + 1)j + λk - λ∈R","λi + (2λ -1)j + λk - λ∈R","λi + (1 - 2λ)j + λk - λ∈R","λi + (2λ + 1)j + λk - λ∈R","λi - (1 + 2λ)j + λk - λ∈R","λi + (2λ -1)j + λk - λ∈R","img: vec_2_solution"},
            {"vec_3","If a b and c are three unit vectors inclined to each other at an angle θ. then the maximum value of θ is","2π/3","π/3","π/2","2π/3","5π/6","img: vec_3_solution"},
            {"vec_4","Vectors 3a - 5b and 2a + b are mutually perpendicular. If a + 4b and b - a are also mutually perpendicualr then the cosine of the angle between a and b is","19/(5√43)","19/(5√43)","19/(3√43)","19/(2√45","19/(6√43)","img: vec_4_solution"},
            {"alg_1","Solve for x. (|x+3|+x)/(x+2)>1","x∈(−5,−2)∪(−1,∞)","x∈(5,2)∪(−1,∞)","x∈(−5,−2)∪(−1,∞)","x∈(5,2)","x∈(−1,∞)","img: alg_1_solution"},
            {"alg_1","The solution set of the inequality 37−(3x+5)≥9x−8(x−3) is","(−∞,2)","(−∞,2)","(−∞,−2)","(−∞,2)","(−∞,−2)","img: alg_1_solution"},
            {"alg_1","Solution of 2x−1=|x+7| is","8","-2","8","-2 and 8","None of these","img: alg_1_solution"},
            {"alg_1","Ravi obtained 70 and 75 marks in first two unit tests. Then the minimum marks he should get in the third test to have an average of at least 60 marks, are","35","45","35","25","None of these","img: alg_1_solution"},

    };

    public String[][] getQuestions(){
        return quizData;
    }
}

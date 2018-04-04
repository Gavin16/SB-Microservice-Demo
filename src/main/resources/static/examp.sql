# https://blog.csdn.net/top__one/article/details/68062540
SELECT * from t_student;
SELECT * from t_course;
SELECT * from t_teacher;
SELECT * from t_sc;

# 1,查询"01"课程比"02"课程成绩高的所有学生的学号;
SELECT DISTINCT t1.SID
FROM t_sc t1, t_sc t2
WHERE t1.SID = t2.SID
      AND t1.CID = 01
      AND t2.CID = 02
      AND t1.score > t2.score;


# 2,查询平均成绩大于60分的同学的学号和平均成绩;
SELECT SID,avg(score) from t_sc GROUP BY SID
HAVING avg(score) > 60;

# 3 查询所有同学的学号,姓名,选课数,总成绩;

SELECT sc.SID "学号",stu.Sname "姓名",count(sc.CID) "选课数",sum(sc.score) "总成绩"
from t_sc sc ,t_student stu
WHERE sc.SID = stu.SID
GROUP BY sc.SID;

# 4,查询姓"李"的老师的个数
SELECT count(TID) from t_teacher where Tname LIKE '李%';

# 5,查询没学过"张三"老师课的同学的学号,姓名;
SELECT SID,Sname from t_student where SID not IN (
  select stu.SID from t_student stu,t_teacher tch,t_sc sc,t_course cs
  WHERE tch.Tname = '张三'
        AND tch.TID = cs.TID
        AND cs.CID = sc.CID
        and sc.SID = stu.SID
);

# 6,查询学过"01"并且也学过编号"02"课程的同学的学号,姓名;
SELECT SID,Sname from t_student s where sid in
  (SELECT SID from t_sc where CID = 01 and SID IN
(select sid from t_sc where CID = 02));

# 7,查询学过"张三"老师所教的所有课的同学的学号,姓名
# 第一:先获取学过张三老师课的同学id,name; 然后获取张三老师所教的所有课程;
# 然后判断学过张三老师课的课程数量 等于 张三老师所教的所有课程的学生id和name
SELECT st.SID,st.Sname from t_student  st WHERE st.SID IN (
  SELECT sc.SID from t_course cs,t_sc sc,t_teacher tch
  where cs.CID = sc.CID
        and tch.TID = cs.TID
        and tch.Tname='张三' GROUP BY sc.SID HAVING count(sc.CID) = (
    SELECT count(1) from t_teacher t1,t_course t2 where t1.Tname = '张三' and t1.TID = t2.TID
  )
);

# 8,查询课程编号"02"的成绩比课程编号"01"课程低的所有同学的学号,姓名
# 首先需要查询课程01的学号和成绩,和课程02的学号和成绩,
# 然后比较相同id的成绩的大小,最后找出在那个结果集里面的学生id即可
# select cs1.TID,cs1.CID from t_course cs1,t_course cs2,t_sc t1,t_sc t2
# where cs1.CID = 01 AND cs2.CID = 02 AND cs1.CID = t1.CID and cs2.CID = t2.CID
# and t1.score > t2.score

# 课程1学号和成绩
SELECT SID,Sname from t_student WHERE SID IN(
SELECT a.sid from (
    (SELECT
       SID,
       score
     FROM t_sc
     WHERE CID = 01) a,
    (SELECT
       SID,
       score
     FROM t_sc
     WHERE CID = 02) b
)
  where a.score > b.score and a.sid = b.sid);
# 9,查询所有课程成绩小于60分的同学的学号,姓名;
SELECT SID,Sname from t_student where SID IN
(SELECT DISTINCT SID from t_sc where score < 60);

# 10查询sc表中没有学全所有课的同学的学号,姓名;
SELECT sc.SID,stu.Sname from t_sc sc,t_student stu
WHERE sc.SID = stu.SID
GROUP BY SID
HAVING count(CID)<(SELECT count(1) from t_course);

# 11查询至少有一门课与学号为"01"的同学所学课程相同 的同学的学号和姓名;
SELECT SID,Sname from t_student where SID IN(
  SELECT DISTINCT sc.SID from t_sc sc
  WHERE sc.SID != 01 AND sc.CID IN
    (SELECT CID FROM t_sc WHERE SID = 01)
);
# 12查询至少学过学号为"01"同学所有一门课的其他同学学号和姓名;



# 13把"SC"表中"张三"老师教的课的成绩都更改为此课程的平均成绩
UPDATE t_sc
SET score = (
  SELECT AVG(score) FROM (SELECT * FROM t_sc) a WHERE a.cid = t_sc.cid)
WHERE t_sc.cid IN (SELECT cid FROM t_course, t_teacher
                      WHERE t_course.tid = t_teacher.tid AND t_teacher.tname = "张三");


UPDATE t_sc
SET score = (SELECT avg(score) from t_sc s where s.cid = t_sc.cid)
WHERE t_sc.SID IN (

)


# 14查询有"02"号的同学学习的所有课程的其他同学学号和姓名;

# 15删除学习"张三"老师课的SC表记录;

# 16向SC表中插入数据,要求符合以下条件:没有上过编号"02"课程的同学学号,02号课,02号课的平均成绩;

# 17按平均成绩从高到低显示所有学生的01"语文",02 "数学",03 "英语"三门的课程成绩
# 按如下形式显示:     学生ID,语文,数学,英语,有效课程数,有效平均分
# 18查询各科成绩最高和最低的分,以如下形式显示:课程ID,最高分,最低分
# 19按各科平均成绩从低到高和及格率的百分数从高到低顺序
# 20查询如下课程平均成绩和及格率的百分数(用"1行"显示): 语文(01),数学(02),英语(03)
# 21查询不同老师所教不同课程平均分从高到低显示

# 22查询如下课程成绩第 3 名到第 6 名的学生成绩单:语文(1),数学(02),英语(03)
# 23统计列各科成绩的各分数段人数:课程ID,课程名称,[100-85],[85-70],[70-60],[<60]
# 24查询学生平均成绩及其名次;
# 25查询各科成绩前三名的记录:(不考虑成绩并列情况)
# 26查询每门课程被选修的学生数
# 27查询出只选修了一门课程的全部学生的学号和姓名
# 28查询男生,女生人数
# 29查询姓"王"的学生名单
# 30查询同名学生名单,并统计同名人数
# 31 1991年出生的学生名单(注:Student表中Sage列的类型是datetime)
# 32查询每门课程的平均成绩,结果按平均成绩升序排列,平均成绩相同时,按课程号降序排列
# 33查询平均成绩大于85的所有学生的学号,姓名和平均成绩
# 34查询课程名称为"语文",且分数低于60的学生姓名和分数
# 35查询所有学生的选课情况;
# 36查询每一门课程成绩在70分以上的姓名,课程名称和分数;
# 37查询不及格的课程,并按课程号从大到小排列
# 38查询课程编号为03且课程成绩在80分以上的学生的学号和姓名;
# 39求选了课程的学生人数
# 40查询选修"张三"老师所授课程的学生中,成绩最高的学生姓名及其成绩
# 41查询各个课程及相应的选修人数
# 42查询不同课程成绩相同的学生的学号,课程号,学生成绩
# 43查询每门功成绩最好的前两名
# 44统计每门课程的学生选修人数(超过5人的课程才统计,此处6可替换)要求输出课程号和选修人数,查询结果     按人数降序排列,查询结果按人数降序排列,若人数相同,按课程号升序排列
# 45检索至少选修两门课程的学生学号
# 46查询全部学生都选修的课程的课程号和课程名
# 47查询两门以上不及格课程的同学的学号及其平均成绩
# 48检索"03"课程分数小于60,按分数降序排列的同学学号
# 49删除"02"同学的"01"课程的成绩
# 50将竖表sc变为横表,随意构造列




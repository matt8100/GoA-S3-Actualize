package com.example.tbdapp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Singleton {
    public ArrayList<Advisor> advisors;
    public User user;
    public HashMap<String, ArrayList<Message>> chatHistory;



    private static Singleton instance;

    private Singleton() {
        advisors = new ArrayList<>();
        createAdvisorProfiles();

        user = new User("Jane Foster", "profile_picture", "Jane", "1996-09-04", "janefoster@tbd.com", "Ontario", "Permanent Resident", "Unemployed", "$10,000", "Tenant", new ArrayList<String>(), "A financial advisor");

        chatHistory = new HashMap<>();
        populateChatHistories();


        populateMessageList("7", "0", "Hi Jane! How has your resume been going?");
        populateMessageList("0", "7", "It's been going pretty good, I think I've got a decent start");
        populateMessageList("7", "0", "That's great to hear! If you have any questions don't hesitate to contact me");

        populateMessageList("1", "0", "Has the financial plan that we set up last week been working out for you?");
        populateMessageList("0", "1", "Yeah, I think it's definitely helping");
        populateMessageList("0", "1", "It's really nice knowng where I've been spending all my money without thinking about it!");
        populateMessageList("1", "0", "Sounds great!");
        populateMessageList("1", "0", "Would you like to have a call sometime tomorrow afternoon to discuss this further?");
        populateMessageList("0", "1", "For sure, would 2:30 work?");
        populateMessageList("1", "0", "Alright, 2:30 it is. See you then!");

        populateMessageList("9", "0", "Hey there Jane! Would you like to schedule a video call to discuss how I can help you?");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static Message getLastMessage(ArrayList<Message> messageList) {
        if(messageList.size() == 0) {
            return null;
        }
        return messageList.get(0);
    }

    private void createAdvisorProfiles() {
        advisors.add(new Advisor("1","Michael Andersen",AdvisorType.FINANCIAL,"I enjoy working with people who care for others and their community – firefighters, therapists, doctors, nurses, and teachers. As you help others, you also must help yourself. That’s where I come in. I help you put all the financial pieces together into a cohesive strategy that matches you and your values.","- Certified Financial Planner®\n- Degree in Business Administration with a concentration in Finance\n- Financial Reporting & Analysis", "advisor_michael_andersen"));
        advisors.add(new Advisor("2","Joanne A. Rojas",AdvisorType.FINANCIAL,"As a Financial Advisor, Joanne collaborates with clients to help achieve their retirement goals. Joanne is passionate about tax efficient investing research, asset allocation and mutual fund manager selection. She is technologically proficient and always eager to learn new skills.","- Tax Preparation\n- General Ledger\n- Specializes in retirement, estate, & legacy strategies", "advisor_joanne_rojas"));
        advisors.add(new Advisor("3", "Sharon Bailey",AdvisorType.FINANCIAL,"I enjoy working with people, numbers and technology. Being a financial planner allows me the pleasure of working with all three. As a financial planner over the past 8 years, I have leveraged my diverse experiences and coupled those with my background in finance to help a number of clients achieve the quality of life they aspired to.","- Certified Financial Planner®\n- BFA™ Behavioral Financial Advisor","advisor_sharon_bailey"));
        advisors.add(new Advisor("4","Faraz Abadi",AdvisorType.FINANCIAL, "Faraz Abadi’s interest in finances began as a young child who wanted to understand the nuances of planning, organizing, and budgeting money. He worked as a CPA at an accounting firm in northern BC before realizing he wanted to pursue the personal planning side of the business as it allowed him to have a broader engagement with clients.","- Doctorate in counselling psychology from UNBC\n- 15 years of experience at Goldstein Inc. Law Firm in Prince Rupert\n- Business level Arabic and Turkish", "advisor_faraz_abadi"));
        advisors.add(new Advisor("5","Park Hae-Jung",AdvisorType.FINANCIAL,"I am passionate about the wealth management industry and working directly with my clients to develop plans and actions that are tailored to their unique circumstances. With extensive experience from working with at-risk clients, I have come to understand my clients very well, and your unique financial needs.","- Fluent in Korean\n- Specializes in estate & legacy strategies","advisor_park_haejung"));
        advisors.add(new Advisor("6", "Finley Clements",AdvisorType.CAREER,"Finley works as a career coach in helping people understand their career goals with expertise in helping unemployed individuals find gainful employment. He has an ability to implement new initiatives regardless of the task or job you’re searching for.\n\nOrganised, proactive, and detail-orientated professional with strong communication and interpersonal abilities, Finley is able to realize any client’s career goals.","- Bachelor’s Degree in counselling from McGill University\n- Business level Mandarin and Cantonese","advisor_finley_clements"));
        advisors.add(new Advisor("7","Nadia Strickland",AdvisorType.CAREER,"Hi! I’m Nadia, a confidence-building, career-boosting, image-polishing copywriter. I’m passionate about helping people see their true potential and assisting them in presenting themselves genuinely yet artfully. Especially in writing.\n\nI understand how first impressions are important to you and since a resume, an email, or a statement of purpose may be your method of first contact, I can help you turn your thoughts and ideas into perfectly moulded words.","- Master of Liberal Arts in Journalism from Harvard\n- Revamped hundreds of personal documents\n- Specializes in helping people from diverse backgrounds express themselves","advisor_nadia_strickland"));
        advisors.add(new Advisor("8","Kristina Shishani",AdvisorType.CAREER,"Hi, I’m Kristina. I embarked on my professional counselling career as a psychotherapist dedicated to alleviating suffering, and over time I came to realize that I was a much better counselor when I focused on strengths development, self-actualization and human flourishing. This realization led me to career coaching.\n\nAs a career coach, I focus on your strengths, help you clarify and develop your unique genius, facilitate greater awareness of your internal resources for better decision-making, and help you cultivate greater resilience when facing uncertainty and change as you move toward more meaningful work in the world.","- Masters in Counseling Psychology (Contemplative Psychology)\n- Specializes in moving through emotional barriers that prevent you achieving the future life and work you envision","advisor_kristina_shishani"));
        advisors.add(new Advisor("9","Liam Burke",AdvisorType.CAREER,"Hi, I’m Liam. I'm a proactive, resourceful & creative career coach. I will help you achieve clarity & success with your career goals while remaining completely aligned with your own personal values and aspirations.\n\nJust like many of my clients, I have transitioned through many careers successfully. The assets that I bring to my work as a career counsellor include 15 years of counselling experience and more than a decade of professional experience in Human Resources.","- Specializes in career transitions\n- Experience in HR & labour relations\n- 6 years in career counselling & coaching","advisor_liam_burke"));
        advisors.add(new Advisor("10","Owen Harding",AdvisorType.CAREER,"Hi! I’m Owen, an accomplished copywriter who digs through your career history to pull out the gems that wow hiring managers and make you stand out from the crowd.\n\nI enjoy learning about the work people do and helping them find the best ways to show off their strengths and achievements. Professionals in all fields and industries have gone on to improve their careers after working with me to craft the perfect resume package.","- Bachelor of Arts in Journalism from Staffordshire University\n- 5 years of experience writing bespoke resumes, cover letters and LinkedIn profiles\n- Remarkable track record of client satisfaction","advisor_owen_harding"));
    }

    private void populateChatHistories() {
        for(int i=0;i<advisors.size();i++) {
            chatHistory.put(advisors.get(i).id, new ArrayList<Message>());
        }
    }

    public void populateMessageList(String senderID, String recipientID, String message) {
        if(Integer.parseInt(senderID) == 0) {
            chatHistory.get(Integer.toString(Integer.parseInt(recipientID))).add(0, new Message("0", message, user, new Date()));
        } else if(Integer.parseInt(recipientID) == 0) {
            chatHistory.get(Integer.toString(Integer.parseInt(senderID))).add(0, new Message("1", message, advisors.get(Integer.parseInt(senderID) - 1), new Date()));
        }
    }
}

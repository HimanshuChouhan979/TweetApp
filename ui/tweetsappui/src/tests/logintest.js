


describe('user login tests', function(){

    it('register user test',function(){
        browser.get( 'http://localhost:4200/register');
        element(by.css('input[name="userName"]')).click();
        element(by.css('input[name="userName"]')).clear().sendKeys('ad3');
        element(by.css('input[name="email"]')).click();
        element(by.css('input[name="email"]')).clear().sendKeys('adeep123@gmail.com');
        element(by.css('button>span:nth-of-type(1)')).click();

        browser.sleep(5000)
    })
   

    it('login user navigate test',function(){
        browser.get( 'http://localhost:4200/register');
        element(by.css('a:nth-of-type(2)>span:nth-of-type(1)')).click();
        

        browser.sleep(5000)
    })

    

    it('sucessful login test',function(){
        browser.get('http://localhost:4200/login');
        element(by.css('input[name="username"]')).click();
        element(by.css('input[name="username"]')).clear().sendKeys('ad');
        element(by.css('input[name="password"]')).click();
        element(by.css('input[name="password"]')).clear().sendKeys('ad');
        element(by.css('button>span:nth-of-type(1)')).click();
        browser.sleep(2000)
    })


    it('user tweet test',function(){
        browser.get( 'http://localhost:4200/user/home');
        element(by.css('app-home>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(2)>form>textarea')).click();
        element(by.css('app-home>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(2)>form>textarea')).clear().sendKeys('my first wteet in tweet app');
        element(by.css('app-home>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(1)>div:nth-of-type(2)>form>button')).click();

        browser.sleep(4000)
    })


   

    it('send otp test',function(){
        browser.get( 'http://localhost:4200/reset');
        element(by.css('input[name="email"]')).click();
        element(by.css('input[name="email"]')).clear().sendKeys('brain2ivor@gmail.com');//
        element(by.css('button>span:nth-of-type(1)')).click();

        browser.sleep(2000)
    })

   



});


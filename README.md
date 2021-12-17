# Instruções

* Banco de dados

    * Restaurar o backup do banco de dados postgresql-13 contido no arquivo "social_media.tar" na raiz do projeto.
  

    * Tabelas:
      
        * "social_media_domain" => Cadastro da rede social, por enquanto, só temos facebook, quando formos desenvolver o instagram, deve ser cadastrado nessa tabela.; 

        * "input" => Tabela de entrada dos dados, o robô vai processar todos os registros dessa tabela que estiver com o atributo flag_process = 0;  

        * "user" => Tabela onde o robô irá salvar os dados do usuário, os principais atributos são: 
            "id_profile" (será utilizado futuramente para encontrar o perfil do usuário, exemplo de url para achar um determinado usuário: "https://www.facebook.com/140076900493504") e 
            "id_photo" (será utilizado futuramente para encontrar a foto o usuário, exemplo de url para achar um determinada foto de usuário: "https://www.facebook.com/590195102148346")
* Config
    * Arquivo config.json (todas as configurações do robô estão contidas nesse arquivo)


    "timeOut": 60000 => Timeout para carregamento da url.
   
    "timeOutElementExists": 15000 => Timeout para verificação se o elemento existe.
    
    "chromedriver_path": "chromedriver.exe" => Caminho do driver selenium chrome
    
    "chromeOptions": ["--disable-notifications", "--start-maximized"] => Opções do selenium
    
    "facebook_login": "renatozanetti9@gmail.com" => Email de login do facebook
    
    "facebook_password": "Ren@to03Thau" => Senha de login do facebook
    
    "minSimilarity": 0.5 => Taxa de similaridade para conicidir nome do banco de dados com o nome do facebook
    
    "minTimeStandBy": 60000 => Menor tempo (em milisegundos) para aguardar a próxima consulta
    
    "maxTimeStandBy": 180000 => Maior tempo (em milisegundos) para aguardar a próxima consulta


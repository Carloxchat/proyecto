<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Mapa de Contacto</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <style>
        #map {
            height: 300px;
            width: 100%;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
            color: #333;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 80%;
            max-width: 600px;
            margin-top: 20px;
        }
        h1 {
            color: #2ecc71; /* Verde Elegante */
            font-size: 24px;
            margin-bottom: 20px;
        }
        h2 {
            color: #2980b9;
            font-size: 20px;
        }
        form {
            margin-top: 20px;
        }
        input[type="text"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        input[type="submit"] {
            background-color: #2ecc71; /* Verde Elegante */
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #27ae60; /* Verde más Oscuro */
        }
    </style>
</head>
<body>
   
    <div class="container">
        <h1>Contacto</h1>
        <div id="map"></div>
        
        <h2>Información de Contacto</h2>
        <p>Ciudad: Fusagasugá</p>
        <p>Teléfono: 305 860 70 27</p>
        <p>Email: Luisavalderrama10@gmail.com</p>
        
        <h2>Envíanos un Mensaje</h2>
        <form action="procesarFormulario" method="post">
            Nombre: <input type="text" name="nombre" required><br>
            Email: <input type="email" name="email" required><br>
            Mensaje: <textarea name="mensaje" rows="5" required></textarea><br>
            <input type="submit" value="Enviar">
        </form>
    </div>

    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <script>
        var map = L.map('map').setView([4.336044, -74.363780], 15);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);
        L.marker([4.336044, -74.363780]).addTo(map)
            .bindPopup('Ubicación: carrera 2 # 3 -36, Fusagasugá.')
            .openPopup();
    </script>
</body>
</html>

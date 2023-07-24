<!DOCTYPE html>
<html>
<head>
    <title>Rock Paper Scissors - Game</title>
</head>
<body>
    <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
    <h2>Wins: <%= session.getAttribute("wins") %></h2>
    <h2>Losses: <%= session.getAttribute("losses") %></h2>
    <h2>Draws: <%= session.getAttribute("draws") %></h2>
    <form action="game" method="post">
        <label for="choice">Choose: </label>
        <select id="choice" name="choice">
            <option value="rock">Rock</option>
            <option value="paper">Paper</option>
            <option value="scissors">Scissors</option>
        </select>
        <button type="submit">Play</button>
    </form>
</body>
</html>
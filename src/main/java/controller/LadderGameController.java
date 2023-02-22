package controller;

import model.Ladder;
import model.LadderHeight;
import model.Line;
import model.Name;
import model.Players;
import model.Result;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.IntStream;

public class LadderGameController {
    public InputView inputView;
    public OutputView outputView;

    public LadderGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = new Players(setPlayerNames());
        List<Result> results = setResults(players.size());
        Ladder ladder = new Ladder(players, setLadderHeight());
        outputView.printAllPlayerNames(players.getAllPlayerNames());
        outputView.printLadder(ladder);
        outputView.printAllResults(results);
        playGame(players, ladder);
    }

    private void playGame(Players players, Ladder ladder) {
        IntStream.range(0, ladder.getHeight())
                .forEach(index -> playOneLine(players, ladder.getLine(index)));
    }

    private void playOneLine(Players players, Line line) {
        players.moveAllPlayersByLinePoints(line.getPoints());
    }

    private List<Result> setResults(int playerCount) {
        outputView.printResultsMessage();
        return inputView.readResults(playerCount);
    }

    private List<Name> setPlayerNames() {
        outputView.printPlayerNamesMessage();
        return inputView.readPlayerNames();
    }

    private LadderHeight setLadderHeight() {
        outputView.printLadderHeightMessage();
        return inputView.readLadderHeight();
    }
}

package org.salandur.advent_of_code.day20;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Group implements PathElement {
    private final Path parent;
    private final LinkedList<Path> paths = new LinkedList<>();

    public Group(Path parent) {
        this.parent = parent;
    }

    @Override
    public int getPathLength() {
        return paths.parallelStream().mapToInt(Path::getPathLength).sum();
    }

    @Override
    public int getLongestPathLength() {
        return isCyclic() ? 0 : paths.parallelStream().mapToInt(Path::getLongestPathLength).max().getAsInt();
    }

    private boolean isCyclic() {
        for (Path p : paths) {
            if (p.getPathLength() == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getPathStrings(String head, List<PathElement> tail) {
        List<String> ps = new ArrayList<>();

        if (isCyclic()) {
            for (Path p : paths) {
                if (p.getPathLength() > 0) {
                    ps.addAll(p.getPathStrings(head));
                }
            }
            if (!tail.isEmpty()) {
                PathElement p = tail.remove(0);
                ps.addAll(p.getPathStrings(head, tail));
            }
        } else {
            for (Path p : paths) {
                ps.addAll(p.getPathStrings(head, tail));
            }
        }

        return ps;

    }

    @Override
    public String getPathLengths() {
        StringBuilder b = new StringBuilder();
        b.append('(');
        b.append(StringUtils.join(paths.stream().map(p -> p.getPathLengths()).collect(toList()), '|'));
        b.append(')');
        return b.toString();
    }

    public Path newPath() {
        Path p = new Path(this);
        paths.add(p);
        return p;
    }

    public Path getParent() {
        return parent;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append('(');
        b.append(StringUtils.join(paths, "|"));
        b.append(')');
        return b.toString();
    }
}
